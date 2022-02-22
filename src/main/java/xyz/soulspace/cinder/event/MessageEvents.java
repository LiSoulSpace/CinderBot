package xyz.soulspace.cinder.event;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.event.events.TempMessageEvent;
import net.mamoe.mirai.message.data.Message;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.soulspace.cinder.command.*;
import xyz.soulspace.cinder.constant.ConstantBlockList;
import xyz.soulspace.cinder.service.KeyWordService;
import xyz.soulspace.cinder.utils.StringUtil;

import java.util.ArrayList;

@Component
public class MessageEvents extends SimpleListenerHost {
    private static final Logger logger = LoggerFactory.getLogger(MessageEvents.class);

    @Autowired
    CommandConfig commandConfig;

    @Autowired
    KeyWordService keyWordService;

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        logger.error("RecallEvent Error:{}", exception.getMessage());
    }


    /**
     * 所有消息处理
     *
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMessage(@NotNull MessageEvent event) throws Exception {
        User sender = event.getSender();
        String oriMsg = event.getMessage().contentToString();

        logger.info("{接收到其他消息} userId:{},userNick:{},msg:{}", sender.getId(), sender.getNick(), event.getMessage().toString());

        //黑名单，用来防止和其他机器人死循环响应，或者屏蔽恶意人员
        if (ConstantBlockList.BLACK_LIST.contains(sender.getId())) {
            return ListeningStatus.LISTENING;
        }

        //是否指令模式
        if (!commandConfig.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }
        EverywhereCommand command = (EverywhereCommand) commandConfig.getCommand(oriMsg, CommandConfig.everywhereCommands);

        if (command == null) {
            return ListeningStatus.LISTENING;
        }
        //执行指令并回复结果
        Message result = command.execute(sender, getArgs(oriMsg), event.getMessage(), event.getSubject());
        if (result != null) {
            event.getSubject().sendMessage(result);
        }

        return ListeningStatus.LISTENING; // 表示继续监听事件
    }


    /**
     * 好友私聊消息事件处理
     *
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    @NotNull
    @EventHandler
    public ListeningStatus onFriendMessage(@NotNull FriendMessageEvent event) throws Exception {
        Friend sender = event.getSender();
        String oriMsg = event.getMessage().contentToString();

        logger.info("{接收到好友消息} userId:{},userNick:{},msg:{}", sender.getId(), sender.getNick(), event.getMessage().toString());

        //黑名单，用来防止和其他机器人死循环响应，或者屏蔽恶意人员
        if (ConstantBlockList.BLACK_LIST.contains(sender.getId())) {
            return ListeningStatus.LISTENING;
        }

        //是否指令模式
        if (!commandConfig.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }
        FriendCommand command = (FriendCommand) commandConfig.getCommand(oriMsg, commandConfig.friendCommands);
        if (command == null) {
            return ListeningStatus.LISTENING;
        }
        //执行指令并回复结果
        Message result = command.execute(sender, getArgs(oriMsg), event.getMessage(), event.getSubject());
        if (result != null) {
            event.getSubject().sendMessage(result);
        }
        //事件拦截 防止everywhere消息事件再次处理
        event.intercept();

        return ListeningStatus.LISTENING;
    }


    /**
     * 群聊消息事件处理
     *
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    @NotNull
    @EventHandler
    public ListeningStatus onGroupMessage(@NotNull GroupMessageEvent event) throws Exception {
        Member sender = event.getSender();
        String oriMsg = event.getMessage().contentToString();

        logger.info("{接收到群消息} groupId:{},userNick:{},userId:{},msg:%{},groupName:{},userCard:{}",
                event.getGroup().getId(), sender.getNick(), sender.getId(), event.getMessage().toString(), event.getGroup().getName(), event.getSender().getNameCard());

        //黑名单，用来防止和其他机器人死循环响应，或者屏蔽恶意人员
        if (ConstantBlockList.BLACK_LIST.contains(sender.getId())) {
            return ListeningStatus.LISTENING;
        }

        //是否指令模式
        if (!commandConfig.isCommand(oriMsg)) {
            // 非指令处理其他业务
            //关键词响应
            keyWordService.keyWordMatchGroup(event);
            return ListeningStatus.LISTENING;
        }
        GroupCommand command = (GroupCommand) commandConfig.getCommand(oriMsg, commandConfig.groupCommands);
        if (command == null) {
            return ListeningStatus.LISTENING;
        }

        //指令参数
        ArrayList<String> args = getArgs(oriMsg);

        //判断权限
        Message result = command.permissionCheck(sender, args, event.getMessage(), event.getSubject());
        if (null != result) {
            event.getSubject().sendMessage(result);
        } else {
            //执行指令并回复结果
            result = command.execute(sender, args, event.getMessage(), event.getSubject());
            if (result != null) {
                event.getSubject().sendMessage(result);
            }
        }
        //事件拦截 防止公共消息事件再次处理
        event.intercept();

        return ListeningStatus.LISTENING;
    }

    /**
     * 群临时消息事件处理
     *
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     * @throws Exception 可以抛出任何异常, 将在 handleException 处理
     */
    @NotNull
    @EventHandler
    public ListeningStatus onTempMessage(@NotNull TempMessageEvent event) throws Exception {
        Member sender = event.getSender();

        String oriMsg = event.getMessage().contentToString();

        logger.info("{接收到临时消息} userId:{},userNick:{},msg:{}", sender.getId(), sender.getNick(), event.getMessage().toString());

        //黑名单，用来防止和其他机器人死循环响应，或者屏蔽恶意人员
        if (ConstantBlockList.BLACK_LIST.contains(sender.getId())) {
            return ListeningStatus.LISTENING;
        }

        //是否指令模式
        if (!commandConfig.isCommand(oriMsg)) {
            return ListeningStatus.LISTENING;
        }

        TempMessageCommand command = (TempMessageCommand) commandConfig.getCommand(oriMsg, commandConfig.tempMsgCommands);
        if (command == null) {
            return ListeningStatus.LISTENING;
        }
        //执行指令并回复结果
        Message result = command.execute(sender, getArgs(oriMsg), event.getMessage(), sender);
        if (result != null) {
            event.getSubject().sendMessage(result);
        }
        //事件拦截 防止公共消息事件再次处理
        event.intercept();

        return ListeningStatus.LISTENING;
    }


    /**
     * 从消息体中获得 用空格分割的参数
     *
     * @param msg 消息
     * @return 分割出来的参数
     */
    private ArrayList<String> getArgs(String msg) {
        String[] args = msg.trim().split(" ");
        ArrayList<String> list = new ArrayList<>();
        for (String arg : args) {
            if (StringUtil.isNotEmpty(arg)) list.add(arg);
        }
        list.remove(0);
        return list;
    }
}

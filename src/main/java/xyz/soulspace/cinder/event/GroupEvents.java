package xyz.soulspace.cinder.event;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.soulspace.cinder.utils.RandomUtil;
import xyz.soulspace.cinder.utils.StringUtil;

import java.util.Arrays;
import java.util.List;

@Component
public class GroupEvents extends SimpleListenerHost {
    //新成员入群消息
    private List<String> memberJoinMsgList = Arrays.asList(
            "欢迎[%s]入群，请自觉遵守群内相关规定",
            "欢迎新朋友[%s]入群"
    );
    private static final Logger logger = LoggerFactory.getLogger(GroupEvents.class);

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        logger.error("RecallEvent Error:{}", exception.getMessage());
    }

    @EventHandler
    public ListeningStatus onMemberJoinGroup(@NotNull MemberJoinEvent.Active event) {
        Group group = event.getGroup();
        User sender = event.getMember();
        logger.info("{新增群员_主动加群} userId:{},userNick:{},groupId:{},groupName:{}", sender.getId(), sender.getNick(), group.getId(), group.getName());
        //groupMemberJoinMsg(group, sender);
        return ListeningStatus.LISTENING; // 表示继续监听事件
    }

    /**
     * 群成员被邀请加群事件
     *
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     */
    @NotNull
    @EventHandler
    public ListeningStatus onMemberJoinGroup(@NotNull MemberJoinEvent.Invite event) {
        Group group = event.getGroup();
        User sender = event.getMember();
        logger.info("{新增群员_被邀请加群} userId:{},userNick:{},groupId:{},groupName:{}", sender.getId(), sender.getNick(), group.getId(), group.getName());
        //groupMemberJoinMsg(group, sender);
        return ListeningStatus.LISTENING; // 表示继续监听事件
    }

    //群成员主动加群，被邀请加群事件业务
//    private void groupMemberJoinMsg(Group group, User sender) {
//        //有人入群，发送对应群的自定义公告
//        String groupNotice = groupNoticeService.getGroupNotice(group.getId());
//        if (StringUtil.isNotEmpty(groupNotice)) {
//            Message result = groupNoticeService.parseGroupNotice(groupNotice, sender);
//            group.sendMessage(result);
//            return;
//        }
//
//        //如果没有自定义公告，则发送默认消息
//        //获取头像
//        String qlogoLocalPath = imageService.getQLogoCq(sender.getId());
//        //上传头像
//        Image miraiImage = rabbitBotService.uploadMiraiImage(qlogoLocalPath);
//
//        //返回消息
//        Message result = MessageUtils.newChain();
//        result = result.plus("").plus(miraiImage).plus("\n");
//        result = result.plus("[").plus(sender.getNick()).plus("]\n");
//        String msg = RandomUtil.rollStrFromList(memberJoinMsgList);
//        result = result.plus(String.format(msg, sender.getNick()));
//        group.sendMessage(result);
//    }
}

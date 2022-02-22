package xyz.soulspace.cinder.event;

import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.contact.UserOrBot;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.NudgeEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.soulspace.cinder.constant.ConstantBlockList;
import xyz.soulspace.cinder.service.NudgeService;

@Component
public class NudgeEvents extends SimpleListenerHost {
    private static final Logger logger = LoggerFactory.getLogger(NudgeEvents.class);

    @Autowired
    private NudgeService nudgeService;

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        logger.error("RecallEvent Error:{}", exception.getMessage());
    }

    /**
     * 戳一戳事件
     *
     * @param event 消息事件
     * @return 监听状态 详见 ListeningStatus
     */
    @NotNull
    @EventHandler
    public ListeningStatus onNudge(@NotNull NudgeEvent event) {
        UserOrBot fromUser = event.getFrom();
        UserOrBot targetUser = event.getTarget();
        logger.info("戳一戳事件,来自:{}({}),目标:{}({})", fromUser.getId(), fromUser.getNick(), targetUser.getId(), targetUser.getNick());

        //黑名单，用来防止和其他机器人死循环响应，或者屏蔽恶意人员
        if (ConstantBlockList.BLACK_LIST.contains(event.getFrom().getId())) {
            return ListeningStatus.LISTENING;
        }

        //服务内自行发送消息
        nudgeService.onNudge(event);
        return ListeningStatus.LISTENING; // 表示继续监听事件
    }
}

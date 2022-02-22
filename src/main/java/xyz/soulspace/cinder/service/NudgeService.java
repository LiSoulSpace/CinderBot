package xyz.soulspace.cinder.service;

import net.mamoe.mirai.event.events.NudgeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NudgeService {
    private static final Logger logger = LoggerFactory.getLogger(NudgeService.class);

    @Value("${bot.account}")
    private Long botAccount;

    /**
     * 接收到戳一戳
     */
    public void onNudge(NudgeEvent event) {
        Long targetId = event.getTarget().getId();
        if (botAccount.equals(targetId)) {
            //如果目标是兔叽，则触发日常语句回复
            String freeTimeMsg = "您好，这里是机器人，同时基于框架mirai和go-cqhttp，现在仍然处于测试阶段。\nOperating by lisoulspace";
            event.getSubject().sendMessage(freeTimeMsg);
        }
    }

}

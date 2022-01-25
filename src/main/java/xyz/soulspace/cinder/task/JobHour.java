package xyz.soulspace.cinder.task;

import net.mamoe.mirai.contact.ContactList;
import net.mamoe.mirai.contact.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.soulspace.cinder.bot.CinderBot;
import xyz.soulspace.cinder.constant.ConstantCommon;
import xyz.soulspace.cinder.pojo.ReString;
import xyz.soulspace.cinder.service.sys.ConfigService;
import xyz.soulspace.cinder.utils.DateUtil;
import xyz.soulspace.cinder.utils.RandomUtil;

import java.util.Date;

@Component
@EnableScheduling
public class JobHour {
    public static final Logger logger = LoggerFactory.getLogger(JobHour.class);

    @Value("${bot.name.cn:}")
    public String cinderBotName;

    @Value("${proxy.check:off}")
    private String proxyCheck;
    //当前时间，方便其他地方使用
    private int hourNow = 0;

    @Autowired
    private ConfigService configService;

    @Scheduled(cron = "0 0 * * * ?")
    public void execute() {
        //刷新当前时间
        hourNow = DateUtil.getHour();

        //报时兔子
        timeRabbit();
        //天气
//        weatherRabbit();

        //全局随机数刷新
        rabbitRandomRefresh();
    }

    private void timeRabbit() {
        //附加短语
        String msgEx = getMsgEx();

        //群报时，时间间隔交给定时器，这里返回值取当前时间即可
        String msg = String.format("这里是%s报时：%s%s", cinderBotName, DateUtil.toString(new Date()), msgEx);
        try {
            //给每个群发送报时
            ContactList<Group> groupList = CinderBot.getBot().getGroups();
            for (Group groupInfo : groupList) {

                groupInfo.sendMessage(msg);
            }
        } catch (Exception ex) {
            logger.error("报时消息发送异常" + ex.toString(), ex);
        }
    }

    private String getMsgEx(){
        switch (hourNow) {
            //半夜0点
            case 0:
                return ConstantCommon.NEXT_LINE + "新的一天开始啦ヽ(#`Д´)ノ";
            //凌晨4点
            case 4:
                return ConstantCommon.NEXT_LINE + "还有人活着嘛，有的话去睡觉哦~";
            //早上7点
            case 7:
                return ConstantCommon.NEXT_LINE + "早上好,该起床了哦~~";
            //中午12点
            case 12:
                return ConstantCommon.NEXT_LINE + "午安，该是吃午饭的时间了";
            //晚上23点
            case 23:
                return ConstantCommon.NEXT_LINE + "已经很晚了，早点休息哦~~";
        }
        return "";
    }

    //全局随机数刷新
    private void rabbitRandomRefresh() {
        if (hourNow != 0) {
            return;
        }

        int rabbitRandomNum = configService.getRabbitRandomNum();

        //生成一个新的随机数
        int randomNum = RandomUtil.roll(100);
        ConstantCommon.common_config.put("rabbitRandomNum", String.valueOf(randomNum));
        configService.refreshConfigFile();

        logger.info("全局随机数刷新,{}->{}", rabbitRandomNum, randomNum);
    }
}

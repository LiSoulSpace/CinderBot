package xyz.soulspace.cinder.bot;

import kotlin.Lazy;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.ListenerHost;
import net.mamoe.mirai.utils.BotConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import xyz.soulspace.cinder.command.CommandConfig;
import xyz.soulspace.cinder.event.GroupEvents;
import xyz.soulspace.cinder.event.MessageEvents;
import xyz.soulspace.cinder.event.NudgeEvents;
import xyz.soulspace.cinder.sys.AnnotateScanner;

import java.util.Arrays;
import java.util.List;

@Component
public class CinderBot implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(CinderBot.class);

    private static Bot bot;

    public static Bot getBot() {
        return bot;
    }

    @Autowired
    private CommandConfig commandConfig;
    @Autowired
    private MessageEvents messageEvents;
    @Autowired
    private GroupEvents groupEvents;
    @Autowired
    private NudgeEvents nudgeEvents;
    @Autowired
    private AnnotateScanner annotateScanner;

    @Value("${bot.account:}")
    private Long botAccount;
    @Value("${bot.pwd:}")
    private String botPwd;
    private static final String deviceInfo = "deviceInfo.json";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.startBot();
    }

    private void startBot() {
        if (botAccount == null || botPwd == null) {
            System.err.println("----请输入Cinder的账号或密码----");
            logger.warn("----未配置Cinder的账号或密码----");
        }

        bot = BotFactory.INSTANCE.newBot(botAccount, botPwd, new BotConfiguration() {
            {
                fileBasedDeviceInfo(deviceInfo);
                setProtocol(MiraiProtocol.ANDROID_WATCH);//TODO 上线改成ANDROID_PHONE
            }
        });
        bot.login();

        //注册指令
        commandConfig.registerCommandHeads();
        commandConfig.registerCommands(annotateScanner.getCommandList());

        //注册事件
        List<ListenerHost> events = Arrays.asList(
                messageEvents,
                groupEvents,
                nudgeEvents
        );
        for (ListenerHost event : events) {
            GlobalEventChannel.INSTANCE.registerListenerHost(event);
        }

        //设置https协议，已解决SSL peer shut down incorrectly的异常
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");

        // 这个和picbotx 还是不太一样 那个不会占用主线程
        // 这里必须要启新线程去跑bot 不然会占用主线程
//        new Thread(() -> {
//            bot.join();
//        }).start();
//        MiraiConsoleTerminalLoader.main(new String[]{});
    }
}

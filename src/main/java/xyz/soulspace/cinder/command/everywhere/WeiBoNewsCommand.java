package xyz.soulspace.cinder.command.everywhere;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.soulspace.cinder.entity.CommandProperties;
import xyz.soulspace.cinder.service.CinderBotService;
import xyz.soulspace.cinder.service.HotPointNewsService;
import xyz.soulspace.cinder.sys.annotate.Command;

import java.util.ArrayList;

@Command
public class WeiBoNewsCommand extends BaseEveryWhereCommand {
    public static final Logger logger = LoggerFactory.getLogger(WeiBoNewsCommand.class);

    @Autowired
    HotPointNewsService hotPointNewsService;
    @Autowired
    CinderBotService cinderBotService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("weibo", "Weibo", "微博");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        logger.info("new ZhiHu Command start");
        MessageChain answerChain = MessageUtils.newChain();
        String localImagePath = hotPointNewsService.getWeiBoHotPoint().getAbsolutePath();
        Image tempMiraiImg = cinderBotService.uploadMiraiImage(localImagePath);
        answerChain = answerChain.plus("").plus(tempMiraiImg).plus("");
        logger.info("messageChain : " + answerChain);
        return answerChain;
    }
}
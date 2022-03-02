package xyz.soulspace.cinder.command.everywhere;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.soulspace.cinder.pojo.CommandProperties;
import xyz.soulspace.cinder.service.MoYuService;
import xyz.soulspace.cinder.sys.annotate.Command;

import java.util.ArrayList;

@Command
public class MoYuCommand extends BaseEveryWhereCommand {
    public static final Logger logger = LoggerFactory.getLogger(MoYuCommand.class);
    @Autowired
    MoYuService moYuService;

    @Override
    public CommandProperties properties() {
        return new CommandProperties("摸鱼", "moyu", "Moyu");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        logger.info("moYu plugin start");
        MessageChain answerChain = MessageUtils.newChain();
        String moYuString = moYuService.moYuString();
        logger.info(moYuString);
        MessageChain messages = answerChain.plus("").plus(moYuString).plus("");
        return messages;
    }
}

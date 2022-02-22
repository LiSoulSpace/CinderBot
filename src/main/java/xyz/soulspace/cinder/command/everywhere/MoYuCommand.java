package xyz.soulspace.cinder.command.everywhere;

import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.User;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.soulspace.cinder.entity.CommandProperties;
import xyz.soulspace.cinder.sys.annotate.Command;

import java.util.ArrayList;

@Command
public class MoYuCommand extends BaseEveryWhereCommand {
    public static final Logger logger = LoggerFactory.getLogger(MoYuCommand.class);

    @Override
    public CommandProperties properties() {
        return new CommandProperties("摸鱼", "moyu", "Moyu");
    }

    @Override
    public Message execute(User sender, ArrayList<String> args, MessageChain messageChain, Contact subject) {
        return null;
    }
}

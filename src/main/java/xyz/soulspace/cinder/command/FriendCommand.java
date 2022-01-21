package xyz.soulspace.cinder.command;

import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.message.data.Message;
import net.mamoe.mirai.message.data.MessageChain;

import java.util.ArrayList;

public interface FriendCommand extends Command{

    Message execute(Friend sender, ArrayList<String> args, MessageChain messageChain, Friend subject);
}

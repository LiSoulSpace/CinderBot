package xyz.soulspace.cinder.command;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.soulspace.cinder.utils.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class CommandConfig {
    private static final Logger logger = LoggerFactory.getLogger(CommandConfig.class);

    private static Set<String> commandHeads = new HashSet<>();

    public static Map<String, Command> everywhereCommands = new HashMap<>();
    public static Map<String, Command> friendCommands = new HashMap<>();
    public static Map<String, Command> groupCommands = new HashMap<>();
    public static Map<String, Command> tempMsgCommands = new HashMap<>();


    /**
     * 注册指令头，一般用 #
     */
    public void registerCommandHeads() {
        String[] heads = initCommandHeads();
        commandHeads.addAll(Arrays.asList(heads));
    }

    /**
     * 注册指令(批量注册)
     * @param commandList 指令列表
     */
    public void registerCommands(List<Command> commandList){
        if(commandList==null||commandList.size()<=0){
            return;
        }
        commandList.forEach(this::registerCommand);
    }

    /**
     * 注册指令
     * @param command 指令
     */
    public void registerCommand(Command command){
        Map<String, Command> tempCommands = new HashMap<>();
        tempCommands.put(command.properties().getName().toLowerCase(), command);
        command.properties().getAlias().forEach(alias->tempCommands.put(alias.toLowerCase(), command));

        //根据事件类型分配指令，方便监听程序调用
        if (command instanceof FriendCommand) {
            friendCommands.putAll(tempCommands);
        } else if (command instanceof GroupCommand) {
            groupCommands.putAll(tempCommands);
        } else if (command instanceof TempMessageCommand) {
            tempMsgCommands.putAll(tempCommands);
        } else if (command instanceof EverywhereCommand) {
            everywhereCommands.putAll(tempCommands);
        } else {
            //未配置的监听，一般不会出现，除非以后腾讯又加了新花样
            logger.warn("发现未知指令类型[{}]，忽略该指令注册", command.properties().getName());
        }
    }

    /**
     * 判断消息是否为指令
     * @param msg 消息本身
     * @return 是否为指令(Boolean)
     */
    public boolean isCommand(String msg){
        return commandHeads.stream().anyMatch(msg::startsWith);
    }

    public Command getCommand(String msg, Map<String, Command> commandMap){
        String[] temp = msg.split(" ");
        String headCommand = temp[0];

        List<String> temps = commandHeads.stream()
                .filter(head -> headCommand.startsWith(head) && StringUtil.isNotEmpty(head))
                .map(head -> headCommand.replaceFirst(head, ""))
                .collect(Collectors.toList());
        String commandStr;
        if (temps.isEmpty()) {
            commandStr = headCommand;
        } else {
            commandStr = temps.get(0);
        }

        return commandMap.getOrDefault(commandStr.toLowerCase(), null);

    }

    /**
     * 初始化消息头部
     * @return 消息头部字符串数组
     */
    public String[] initCommandHeads(){
        String[] heads = new String[]{
                "#"
        };
        return heads;
    }
}

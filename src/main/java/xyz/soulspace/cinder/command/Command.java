package xyz.soulspace.cinder.command;

import xyz.soulspace.cinder.pojo.CommandProperties;

public interface Command {

    /**
     * 构造指令名称以及别称
     * 不区分大小写
     * @return 指令名称对象
     */
    CommandProperties properties();
}

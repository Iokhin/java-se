package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;

abstract public class AbstractCommand {

    public Bootstrap bootstrap;

    abstract public String getName();
    abstract public String getDescription();
    abstract public void execute();

    public AbstractCommand (Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

}

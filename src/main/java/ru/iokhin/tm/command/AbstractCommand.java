package ru.iokhin.tm.command;

import ru.iokhin.tm.Bootstrap;

abstract public class AbstractCommand {

    public Bootstrap bootstrap;

    abstract public boolean security();

    abstract public String name();

    abstract public String description();

    abstract public void execute();

    public AbstractCommand (Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

}

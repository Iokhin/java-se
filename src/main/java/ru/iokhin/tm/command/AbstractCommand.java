package ru.iokhin.tm.command;

import ru.iokhin.tm.Bootstrap;

abstract public class AbstractCommand {

    public Bootstrap bootstrap;
    private String name;
    private String description;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    abstract public void execute();

    public AbstractCommand (Bootstrap bootstrap, String name, String description) {
        this.bootstrap = bootstrap;
        this.name = name;
        this.description = description;
    }

}

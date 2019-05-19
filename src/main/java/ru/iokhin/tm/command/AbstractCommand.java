package ru.iokhin.tm.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.iokhin.tm.Bootstrap;

@NoArgsConstructor
@AllArgsConstructor
abstract public class AbstractCommand {

    public Bootstrap bootstrap;

    abstract public boolean security();

    abstract public String name();

    abstract public String description();

    abstract public void execute();

}

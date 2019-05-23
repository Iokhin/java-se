package ru.iokhin.tm.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.api.service.IServiceLocator;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
abstract public class AbstractCommand {

    protected IServiceLocator serviceLocator;

    protected Bootstrap bootstrap;

    abstract public boolean security();

    abstract public String name();

    abstract public String description();

    abstract public void execute();

}

package ru.iokhin.tm.command;

import lombok.*;

import javax.enterprise.context.ApplicationScoped;

@NoArgsConstructor
@Setter
@Getter
//@ApplicationScoped
abstract public class AbstractCommand {

    abstract public boolean security();

    abstract public boolean admin();

    abstract public String name();

    abstract public String description();

    abstract public void execute();

}

package ru.iokhin.tm.command;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
abstract public class AbstractCommand {

    abstract public boolean security();

    abstract public boolean admin();

    abstract public String name();

    abstract public String description();

    abstract public void execute();

}

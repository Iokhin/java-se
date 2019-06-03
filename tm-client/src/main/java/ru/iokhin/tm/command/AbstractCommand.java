package ru.iokhin.tm.command;

import lombok.*;
import ru.iokhin.tm.api.IEndpointServiceLocator;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
abstract public class AbstractCommand {

    protected IEndpointServiceLocator endpointServiceLocator;

    abstract public boolean security();

    abstract public boolean admin();

    abstract public String name();

    abstract public String description();

    abstract public void execute();

}

package ru.iokhin.tm.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.iokhin.tm.api.IEndpointServiceLocator;
import ru.iokhin.tm.endpoint.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
abstract public class AbstractCommand {

    protected IEndpointServiceLocator endpointServiceLocator;

    abstract public boolean security();

    abstract public String name();

    abstract public String description();

    abstract public void execute() throws AuthException_Exception, IOException_Exception, JAXBException_Exception, ClassNotFoundException_Exception;

}

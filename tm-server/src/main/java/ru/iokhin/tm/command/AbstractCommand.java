package ru.iokhin.tm.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.exeption.AuthException;

import javax.xml.soap.SOAPException;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
abstract public class AbstractCommand {

    protected IServiceLocator serviceLocator;

    abstract public boolean security();

    abstract public String name();

    abstract public String description();

    abstract public void execute() throws AuthException;

}

package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ClassNotFoundException_Exception;
import ru.iokhin.tm.endpoint.IOException_Exception;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataLoadSerializedCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "data-load";
    }

    @Override
    public String description() {
        return "Load serialized objects";
    }

    @Override
    public void execute() throws ClassNotFoundException_Exception, IOException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataBinLoad();
        System.out.println("SUCCESS");
    }
}
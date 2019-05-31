package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.IOException_Exception;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataSaveSerializedCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public boolean admin() {
        return true;
    }

    @Override
    public String name() {
        return "data-save";
    }

    @Override
    public String description() {
        return "Save serialized objects in output file";
    }

    @Override
    public void execute() throws IOException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataBinSave();
        System.out.println("SUCCESS");
    }
}

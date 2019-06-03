package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

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
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataBinSave();
        System.out.println("SUCCESS");
    }
}

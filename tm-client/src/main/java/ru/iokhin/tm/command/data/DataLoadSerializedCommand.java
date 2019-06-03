package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataLoadSerializedCommand extends AbstractCommand {
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
        return "data-load";
    }

    @Override
    public String description() {
        return "Load serialized objects";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataBinLoad();
        System.out.println("SUCCESS");
    }
}

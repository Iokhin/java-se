package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataSaveFasterJSONCommand extends AbstractCommand {
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
        return "faster-json-save";
    }

    @Override
    public String description() {
        return "Load data from Faster JSON";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataFasterJSONSave();
        System.out.println("SUCCESS");
    }
}

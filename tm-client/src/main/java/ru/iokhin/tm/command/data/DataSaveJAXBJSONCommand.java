package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataSaveJAXBJSONCommand extends AbstractCommand {
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
        return "jaxb-json-save";
    }

    @Override
    public String description() {
        return "Save data into JAX-B JSON";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataJAXBJSONSave();
        System.out.println("SUCCESS");
    }
}

package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataLoadJAXBFromJSONCommand extends AbstractCommand {
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
        return "jaxb-json-load";
    }

    @Override
    public String description() {
        return "Load data from JAX-B JSON";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataJAXBJSONLoad();
        System.out.println("SUCCESS");
    }
}

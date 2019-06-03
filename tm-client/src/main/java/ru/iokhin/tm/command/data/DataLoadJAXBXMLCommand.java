package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataLoadJAXBXMLCommand extends AbstractCommand {
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
        return "jaxb-xml-load";
    }

    @Override
    public String description() {
        return "Load data from JAX-B XML";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataJAXBXMLLoad();
        System.out.println("SUCCESS");
    }
}

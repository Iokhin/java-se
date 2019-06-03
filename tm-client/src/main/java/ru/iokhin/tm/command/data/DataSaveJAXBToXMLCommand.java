package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataSaveJAXBToXMLCommand extends AbstractCommand {
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
        return "jaxb-xml-save";
    }

    @Override
    public String description() {
        return "Save data into JAX-B XML";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataJAXBXMLSave();
        System.out.println("SUCCESS");
    }
}

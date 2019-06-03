package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataSaveFasterXMLCommand extends AbstractCommand {
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
        return "faster-xml-save";
    }

    @Override
    public String description() {
        return "Save data into Faster XML";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataFasterXMLSave();
        System.out.println("SUCCESS");
    }
}

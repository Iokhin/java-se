package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

public class DataLoadFasterXMLCommand extends AbstractCommand {
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
        return "faster-xml-load";
    }

    @Override
    public String description() {
        return "Load data from Faster XML";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getUserEndpointBean().dataFasterJSONLoad();
        System.out.println("SUCCESS");
    }
}

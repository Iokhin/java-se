package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

import javax.inject.Inject;

public class DataLoadJAXBXMLCommand extends AbstractCommand {

    @Inject
    private UserEndpointBean userEndpointBean;

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
        userEndpointBean.dataJAXBXMLLoad();
        System.out.println("SUCCESS");
    }
}

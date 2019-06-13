package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

import javax.inject.Inject;

public class DataLoadFasterXMLCommand extends AbstractCommand {

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
        return "faster-xml-load";
    }

    @Override
    public String description() {
        return "Load data from Faster XML";
    }

    @Override
    public void execute() {
        userEndpointBean.dataFasterJSONLoad();
        System.out.println("SUCCESS");
    }
}

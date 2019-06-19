package ru.iokhin.tm.command.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

@Component
public class DataLoadJAXBXMLCommand extends AbstractCommand {

    @Autowired
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

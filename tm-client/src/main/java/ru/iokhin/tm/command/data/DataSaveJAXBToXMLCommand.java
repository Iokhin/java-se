package ru.iokhin.tm.command.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

@Component
public class DataSaveJAXBToXMLCommand extends AbstractCommand {

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
        return "jaxb-xml-save";
    }

    @Override
    public String description() {
        return "Save data into JAX-B XML";
    }

    @Override
    public void execute() {
        userEndpointBean.dataJAXBXMLSave();
        System.out.println("SUCCESS");
    }
}

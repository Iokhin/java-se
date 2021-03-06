package ru.iokhin.tm.command.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

@Component
public class DataSaveJAXBJSONCommand extends AbstractCommand {

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
        return "jaxb-json-save";
    }

    @Override
    public String description() {
        return "Save data into JAX-B JSON";
    }

    @Override
    public void execute() {
        userEndpointBean.dataJAXBJSONSave();
        System.out.println("SUCCESS");
    }
}

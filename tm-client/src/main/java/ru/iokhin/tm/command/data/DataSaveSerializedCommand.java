package ru.iokhin.tm.command.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

@Component
public class DataSaveSerializedCommand extends AbstractCommand {

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
        return "data-save";
    }

    @Override
    public String description() {
        return "Save serialized objects in output file";
    }

    @Override
    public void execute() {
        userEndpointBean.dataBinSave();
        System.out.println("SUCCESS");
    }
}

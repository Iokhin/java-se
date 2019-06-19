package ru.iokhin.tm.command.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

@Component
public class DataLoadSerializedCommand extends AbstractCommand {

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
        return "data-load";
    }

    @Override
    public String description() {
        return "Load serialized objects";
    }

    @Override
    public void execute() {
        userEndpointBean.dataBinLoad();
        System.out.println("SUCCESS");
    }
}

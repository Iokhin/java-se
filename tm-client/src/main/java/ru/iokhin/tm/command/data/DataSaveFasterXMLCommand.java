package ru.iokhin.tm.command.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

@Component
public class DataSaveFasterXMLCommand extends AbstractCommand {

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
        return "faster-xml-save";
    }

    @Override
    public String description() {
        return "Save data into Faster XML";
    }

    @Override
    public void execute() {
        userEndpointBean.dataFasterXMLSave();
        System.out.println("SUCCESS");
    }
}

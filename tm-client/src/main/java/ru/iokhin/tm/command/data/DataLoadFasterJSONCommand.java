package ru.iokhin.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

import javax.inject.Inject;

public class DataLoadFasterJSONCommand extends AbstractCommand {

    @Inject
    @NotNull
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
        return "faster-json-load";
    }

    @Override
    public String description() {
        return "Load data from Faster JSON";
    }

    @Override
    public void execute() {
        userEndpointBean.dataFasterJSONLoad();
        System.out.println("SUCCESS");
    }
}

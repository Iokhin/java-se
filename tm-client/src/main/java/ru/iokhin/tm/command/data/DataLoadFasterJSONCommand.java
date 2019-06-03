package ru.iokhin.tm.command.data;

import lombok.SneakyThrows;
import ru.iokhin.tm.command.AbstractCommand;

public class DataLoadFasterJSONCommand extends AbstractCommand {
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
        endpointServiceLocator.getUserEndpointBean().dataFasterJSONLoad();
        System.out.println("SUCCESS");
    }
}

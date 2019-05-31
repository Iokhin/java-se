package ru.iokhin.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.IOException_Exception;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaveFasterJSONCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "faster-json-save";
    }

    @Override
    public String description() {
        return "Load data from Faster JSON";
    }

    @Override
    public void execute() throws IOException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataFasterJSONSave();
        System.out.println("SUCCESS");
    }
}

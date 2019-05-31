package ru.iokhin.tm.command.data;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.IOException_Exception;

import java.io.File;
import java.io.IOException;

public class DataLoadFasterXMLCommand extends AbstractCommand {
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
    public void execute() throws IOException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataFasterJSONLoad();
        System.out.println("SUCCESS");
    }
}

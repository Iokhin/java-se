package ru.iokhin.tm.command.data;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.IOException_Exception;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaveFasterXMLCommand extends AbstractCommand {
    @Override
    public boolean security() {
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
    public void execute() throws IOException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataFasterXMLSave();
        System.out.println("SUCCESS");
    }
}

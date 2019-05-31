package ru.iokhin.tm.command.data;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.JAXBException_Exception;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

public class DataSaveJAXBToXMLCommand extends AbstractCommand {
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
    public void execute() throws JAXBException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataJAXBXMLSave();
        System.out.println("SUCCESS");
    }
}

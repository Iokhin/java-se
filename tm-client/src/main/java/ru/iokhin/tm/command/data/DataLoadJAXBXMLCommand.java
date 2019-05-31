package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.JAXBException_Exception;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class DataLoadJAXBXMLCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "jaxb-xml-load";
    }

    @Override
    public String description() {
        return "Load data from JAX-B XML";
    }

    @Override
    public void execute() throws JAXBException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataJAXBXMLLoad();
        System.out.println("SUCCESS");
    }
}

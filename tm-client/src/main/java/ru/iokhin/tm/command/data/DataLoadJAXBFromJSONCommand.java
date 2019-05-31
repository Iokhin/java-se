package ru.iokhin.tm.command.data;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.JAXBException_Exception;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class DataLoadJAXBFromJSONCommand extends AbstractCommand {
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
        return "jaxb-json-load";
    }

    @Override
    public String description() {
        return "Load data from JAX-B JSON";
    }

    @Override
    public void execute() throws JAXBException_Exception {
        endpointServiceLocator.getUserEndpointBean().dataJAXBJSONLoad();
        System.out.println("SUCCESS");
    }
}

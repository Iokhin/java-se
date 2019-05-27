package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.util.DataScope;

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
    public void execute() {
        DataScope dataScope;
        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataScope.class);
            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            dataScope = (DataScope)unmarshaller.unmarshal(new File("jaxb.xml"));
            dataScope.getProjects().forEach(serviceLocator.getProjectService()::merge);
            dataScope.getTasks().forEach(serviceLocator.getTaskService()::merge);
            dataScope.getUsers().forEach(serviceLocator.getUserService()::merge);
            System.out.println("SUCCESS");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

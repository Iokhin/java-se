package ru.iokhin.tm.command.data;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

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
    public String name() {
        return "jaxb-json-load";
    }

    @Override
    public String description() {
        return "Load data from JAX-B JSON";
    }

    @Override
    public void execute() {
//        @NotNull final IProjectService projectService = serviceLocator.getProjectService();
//        @NotNull final ITaskService taskService = serviceLocator.getTaskService();
//        @NotNull final IUserService userService = serviceLocator.getUserService();
//        DataScope dataScope;
//        final JAXBContext jaxbContext;
//        try {
//            jaxbContext = JAXBContext.newInstance(DataScope.class);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
//            dataScope = (DataScope)unmarshaller.unmarshal(new File("jaxb.json"));
//            dataScope.getUsers().forEach(userService::merge);
//            dataScope.getProjects().forEach(projectService::merge);
//            dataScope.getTasks().forEach(taskService::merge);
//            System.out.println("SUCCESS");
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
    }
}

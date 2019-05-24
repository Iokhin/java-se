package ru.iokhin.tm.command.data;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.util.DataScope;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;

public class DataSaveJAXBJSONCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return false;
    }

    @Override
    public String name() {
        return "jaxb-json-save";
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public void execute() {
        @NotNull final IProjectService projectService = serviceLocator.getProjectService();
        @NotNull final ITaskService taskService = serviceLocator.getTaskService();
        @NotNull final IUserService userService = serviceLocator.getUserService();
        @NotNull final DataScope dataScope = new DataScope(new ArrayList<>(projectService.findAll()), new ArrayList<>(taskService.findAll()),
                new ArrayList<>(userService.findAll()));
        final JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(DataScope.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
            marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(dataScope, new File("jaxb.json"));
            System.out.println("SUCCESS");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

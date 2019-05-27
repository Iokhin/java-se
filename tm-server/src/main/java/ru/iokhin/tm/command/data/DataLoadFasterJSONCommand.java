package ru.iokhin.tm.command.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.util.DataScope;

import java.io.File;
import java.io.IOException;

public class DataLoadFasterJSONCommand extends AbstractCommand {
    @Override
    public boolean security() {
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
        @NotNull final IProjectService projectService = serviceLocator.getProjectService();
        @NotNull final IUserService userService = serviceLocator.getUserService();
        @NotNull final ITaskService taskService = serviceLocator.getTaskService();
        DataScope dataScope;
        ObjectMapper mapper = new ObjectMapper();
        try {
            dataScope = mapper.readValue(new File("faster.json"), DataScope.class);
            dataScope.getUsers().forEach(userService::merge);
            dataScope.getProjects().forEach(projectService::merge);
            dataScope.getTasks().forEach(taskService::merge);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

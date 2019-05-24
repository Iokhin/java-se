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
import java.util.ArrayList;

public class DataSaveFasterJSONCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return false;
    }

    @Override
    public String name() {
        return "faster-json-save";
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public void execute() {
        @NotNull final IProjectService projectService = serviceLocator.getProjectService();
        @NotNull final IUserService userService = serviceLocator.getUserService();
        @NotNull final ITaskService taskService = serviceLocator.getTaskService();
        DataScope dataScope = new DataScope(new ArrayList<>(projectService.findAll()),
                new ArrayList<>(taskService.findAll()), new ArrayList<>(userService.findAll()));
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("faster.json"), dataScope);
            System.out.println("SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

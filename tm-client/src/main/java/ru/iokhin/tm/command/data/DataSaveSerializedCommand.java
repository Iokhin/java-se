package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataSaveSerializedCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "data-save";
    }

    @Override
    public String description() {
        return "Save serialized objects in output file";
    }

    @Override
    public void execute() {
//        IProjectService projectService = serviceLocator.getProjectService();
//        ITaskService taskService = serviceLocator.getTaskService();
//        IUserService userService = serviceLocator.getUserService();
//        DataScope dataScope = new DataScope(new ArrayList<>(projectService.findAll()), new ArrayList<>(taskService.findAll()),
//                new ArrayList<>(userService.findAll()));
//        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("serializedData"))) {
//            objectOutputStream.writeObject(dataScope);
//            objectOutputStream.flush();
//            System.out.println("SUCCESS");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

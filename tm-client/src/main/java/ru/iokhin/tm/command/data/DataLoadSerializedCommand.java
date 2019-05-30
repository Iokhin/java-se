package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DataLoadSerializedCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "data-load";
    }

    @Override
    public String description() {
        return "Load serialized objects";
    }

    @Override
    public void execute() {
//        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serializedData"))) {
//            DataScope dataScope = (DataScope)objectInputStream.readObject();
//            dataScope.getProjects().forEach(serviceLocator.getProjectService()::merge);
//            dataScope.getTasks().forEach(serviceLocator.getTaskService()::merge);
//            dataScope.getUsers().forEach(serviceLocator.getUserService()::merge);
//            System.out.println("SUCCESS");
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}

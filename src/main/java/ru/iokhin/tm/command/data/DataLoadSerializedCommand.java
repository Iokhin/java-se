package ru.iokhin.tm.command.data;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.util.DataScope;

import java.io.*;

public class DataLoadSerializedCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return false;
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
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("serializedData"))) {
            DataScope dataScope = (DataScope)objectInputStream.readObject();
            dataScope.getProjectArrayList().forEach(serviceLocator.getProjectService()::merge);
            dataScope.getTaskArrayList().forEach(serviceLocator.getTaskService()::merge);
            dataScope.getUserArrayList().forEach(serviceLocator.getUserService()::merge);
            System.out.println("SUCCESS");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

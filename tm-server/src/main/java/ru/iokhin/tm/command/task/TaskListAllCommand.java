package ru.iokhin.tm.command.task;

import ru.iokhin.tm.command.AbstractCommand;

public class TaskListAllCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-list-all";
    }

    @Override
    public String description() {
        return "List all tasks for current user";
    }

    @Override
    public void execute() {
        System.out.println("TASK LIST:");
        serviceLocator.getTaskService().findAllByUser(serviceLocator.getUserService().getCurrentUser()).forEach(System.out::println);
    }
}

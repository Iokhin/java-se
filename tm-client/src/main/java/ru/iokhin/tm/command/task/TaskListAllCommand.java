package ru.iokhin.tm.command.task;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;

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
    public void execute() throws AuthException_Exception {
        System.out.println("TASK LIST:");
        endpointServiceLocator.getTaskEndpointBean().findAllByUserId(endpointServiceLocator.getSession()).forEach(System.out::println);
    }
}

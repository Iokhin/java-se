package ru.iokhin.tm.command.task;

import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.User;

public class TaskRemoveAllByUserCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public boolean admin() {
        return false;
    }

    @Override
    public String name() {
        return "task-clear-all";
    }

    @Override
    public String description() {
        return "Remove all tasks for current user";
    }

    @Override
    public void execute() {
        endpointServiceLocator.getTaskEndpointBean().removeAllTaskByUserId(endpointServiceLocator.getSession());
        User user = endpointServiceLocator.getUserEndpointBean().findUserById(endpointServiceLocator.getSession().getParentId());
        System.out.println("ALL TASKS FOR " + user.getLogin() + " WAS REMOVED");
    }
}

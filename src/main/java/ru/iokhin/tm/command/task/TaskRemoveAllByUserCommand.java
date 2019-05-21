package ru.iokhin.tm.command.task;

import ru.iokhin.tm.command.AbstractCommand;

public class TaskRemoveAllByUserCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
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
        bootstrap.getTaskService().removeAllByUserId(bootstrap.getCurrentUser().getId());
        System.out.println("ALL TASKS FOR " + bootstrap.getCurrentUser().getLogin() + " WAS REMOVED");
    }
}

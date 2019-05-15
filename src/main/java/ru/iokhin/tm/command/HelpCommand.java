package ru.iokhin.tm.command;

import ru.iokhin.tm.Bootstrap;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        helpCommand();
    }

    private void helpCommand() {
        System.out.println("help: Show all commands.");

        System.out.println("project-create: Create new project.");
        System.out.println("project-list: Show all projects.");
        System.out.println("project-remove: Remove selected project");
        System.out.println("project-clear: Remove all projects");
        System.out.println("project-edit: Edit selected project.");

        System.out.println("task-create: Create new task.");
        System.out.println("task-list: Show all tasks for chosen project.");
        System.out.println("task-remove: Remove selected task");
        System.out.println("task-clear: Remove all tasks for chosen project");
        System.out.println("task-edit: Edit selected task.");

        System.out.println("user-login: Authorize user");
        System.out.println("user-logout: Log out current user");
        System.out.println("user-pass-change: Change the current user's password");
        System.out.println("user-registration: Register a new user");
        System.out.println("user-edit: Edit the current user's profile");
        System.out.println("user-list: List all users");

        System.out.println("exit: Exit task manager");
    }
}

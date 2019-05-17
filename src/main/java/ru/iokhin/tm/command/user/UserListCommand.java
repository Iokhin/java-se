package ru.iokhin.tm.command.user;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class UserListCommand extends AbstractCommand {

    public UserListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserListCommand() {
    }

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "user-list";
    }

    @Override
    public String description() {
        return "List all users";
    }

    @Override
    public void execute() {
        System.out.println("USERS LIST:");
        bootstrap.getUserService().listUser();
    }
}

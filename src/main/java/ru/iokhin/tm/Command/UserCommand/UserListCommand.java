package ru.iokhin.tm.Command.UserCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.Command.AbstractCommand;

public class UserListCommand extends AbstractCommand {

    private static final String name = "user-list";
    private static final String description = "user-list: List all users";

    public UserListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        System.out.println("USERS LIST:");
        bootstrap.getUserService().listUser();
    }
}

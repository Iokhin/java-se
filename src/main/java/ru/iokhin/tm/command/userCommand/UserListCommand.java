package ru.iokhin.tm.command.userCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class UserListCommand extends AbstractCommand {

    public UserListCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("USERS LIST:");
        bootstrap.getUserService().listUser();
    }
}

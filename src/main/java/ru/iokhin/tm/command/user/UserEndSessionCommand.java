package ru.iokhin.tm.command.user;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class UserEndSessionCommand extends AbstractCommand {

    public UserEndSessionCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "user-logout";
    }

    @Override
    public String description() {
        return "Log out user";
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println(bootstrap.getCurrentUser().getLogin() + " was logged out");
        bootstrap.setCurrentUser(null);
        AbstractCommand userAuthorization = new UserAuthorizationCommand(bootstrap);
        userAuthorization.execute();
    }
}

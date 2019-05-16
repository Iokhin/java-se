package ru.iokhin.tm.command.userCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class UserEndSessionCommand extends AbstractCommand {

    public UserEndSessionCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println(bootstrap.getCurrentUser().getLogin() + " was logged out");
        bootstrap.setCurrentUser(null);
        AbstractCommand userAuthorization = new UserAuthorizationCommand(bootstrap, "user-login", "user-login: Authorize user");
        userAuthorization.execute();
    }
}

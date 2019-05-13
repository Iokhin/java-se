package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;

public class UserEndSessionCommand extends AbstractCommand {

    private static final String name = "user-logout";
    private static final String description = "user-logout: Log out current user";

    public UserEndSessionCommand(Bootstrap bootstrap) {
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
        System.out.println(bootstrap.getCurrentUser().getLogin() + " was logged out");
        bootstrap.setCurrentUser(null);
        AbstractCommand userAuthorization = new UserAuthorizationCommand(bootstrap);
        userAuthorization.execute();
    }
}

package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public class UserEndSessionCommand extends AbstractCommand {

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
        System.out.println(serviceLocator.getUserService().getCurrentUser().getLogin() + " was logged out");
        serviceLocator.getUserService().setCurrentUser(null);
    }
}

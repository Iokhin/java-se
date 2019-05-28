package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public class UserProfileEditCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "user-editUser";
    }

    @Override
    public String description() {
        return "Edit current user's profile";
    }

    @Override
    public void execute() {
        System.out.println("USER PROFILE:");
        System.out.println("USER ID: " + serviceLocator.getUserService().getCurrentUser().getId());
        System.out.println("USER LOGIN: " + serviceLocator.getUserService().getCurrentUser().getLogin());
        System.out.println("USER RIGHTS: " + serviceLocator.getUserService().getCurrentUser().getRoleType().displayName());
        System.out.println("ENTER NEW LOGIN TO EDIT");
        @NotNull final String login = serviceLocator.getTerminalService().nextLine();
        if (serviceLocator.getUserService().findByLogin(login) != null) {
            System.out.println("SUCH LOGIN ALREADY EXIST");
            return;
        }
        serviceLocator.getUserService().getCurrentUser().setLogin(login);
        System.out.println("SUCCESS");
    }
}
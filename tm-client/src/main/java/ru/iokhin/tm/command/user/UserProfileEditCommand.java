package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.User;

@NoArgsConstructor
public class UserProfileEditCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public boolean admin() {
        return false;
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
        @Nullable User currentUser = endpointServiceLocator.getUserEndpointBean().findUserById(endpointServiceLocator.getSession().getParentId());
        System.out.println("USER PROFILE:");
        System.out.println("USER ID: " + currentUser.getId());
        System.out.println("USER LOGIN: " + currentUser.getLogin());
        System.out.println("USER RIGHTS: " + currentUser.getRoleType());
        System.out.println("ENTER NEW LOGIN TO EDIT");
        @NotNull final String login = endpointServiceLocator.getTerminalService().nextLine();
        System.out.println("ENTER NEW LOGIN TO EDIT");
        @NotNull final String password = endpointServiceLocator.getTerminalService().nextLine();
        endpointServiceLocator.getUserEndpointBean().editUser(endpointServiceLocator.getSession(), login, password);
        System.out.println("SUCCESS");
    }
}
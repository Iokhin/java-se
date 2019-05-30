package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;

@NoArgsConstructor
public class UserPasswordChangeCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "user-password-change";
    }

    @Override
    public String description() {
        return "Change password for current user";
    }

    @Override
    public void execute() {
        UserEndpointBean userEndpoint = endpointServiceLocator.getUserEndpointBean();
        System.out.println("ENTER THE CURRENT PASSWORD");
        @NotNull final String oldPassword = endpointServiceLocator.getTerminalService().nextLine();
        System.out.println("ENTER THE CURRENT PASSWORD");
        @NotNull final String newPassword = endpointServiceLocator.getTerminalService().nextLine();
        if (!userEndpoint.passChange(oldPassword, newPassword)) {
            System.out.println("WRONG PASSWORD, TRY AGAIN");
            return;
        }
        System.out.println("SUCCESS");
    }

}
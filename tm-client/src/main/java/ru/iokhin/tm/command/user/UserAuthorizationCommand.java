package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;
import ru.iokhin.tm.endpoint.Session;

@NoArgsConstructor
public class UserAuthorizationCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return false;
    }

    @Override
    public String name() {
        return "user-login";
    }

    @Override
    public String description() {
        return "Log in user";
    }

    @Override
    public void execute() throws AuthException_Exception {
        System.out.println("PLEASE ENTER YOUR LOGIN");
        @NotNull final String login = endpointServiceLocator.getTerminalService().nextLine();
        System.out.println("PLEASE ENTER YOUR PASSWORD");
        @NotNull final String password = endpointServiceLocator.getTerminalService().nextLine();
        Session session = endpointServiceLocator.getUserEndpointBean().authUser(login, password);
        if (session == null) {
            System.out.println("WRONG LOGIN OR PASSWORD");
            return;
        }
        endpointServiceLocator.setSession(session);
        System.out.println("WELCOME, " +
                endpointServiceLocator.getUserEndpointBean().findById(session.getParentId()).getLogin() + ", " +
                endpointServiceLocator.getUserEndpointBean().findById(session.getParentId()).getId());
    }
}
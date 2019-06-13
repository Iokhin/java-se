package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.SessionDTO;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import javax.inject.Inject;

@NoArgsConstructor
public class UserAuthorizationCommand extends AbstractCommand {

    @Inject
    @NotNull
    private SessionService sessionService;

    @Inject
    @NotNull
    private UserEndpointBean userEndpointBean;

    @Inject
    @NotNull
    private TerminalService terminalService;

    @Override
    public boolean security() {
        return false;
    }

    @Override
    public boolean admin() {
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
    public void execute() {
        System.out.println("PLEASE ENTER YOUR LOGIN");
        @NotNull final String login = this.terminalService.nextLine();
        System.out.println("PLEASE ENTER YOUR PASSWORD");
        @NotNull final String password = terminalService.nextLine();
        SessionDTO session = userEndpointBean.authUser(login, password);
        if (session == null) {
            System.out.println("WRONG LOGIN OR PASSWORD");
            return;
        }
        sessionService.setSession(session);
        System.out.println("WELCOME, " +
                userEndpointBean.findUserById(session.getParentId()).getLogin() + ", " +
                userEndpointBean.findUserById(session.getParentId()).getId());
    }
}
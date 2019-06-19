package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.SessionDTO;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

@Component
@NoArgsConstructor
public class UserAuthorizationCommand extends AbstractCommand {

    @Autowired
    @NotNull
    private SessionService sessionService;

    @Autowired
    @NotNull
    private UserEndpointBean userEndpointBean;

    @Autowired
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
package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.SessionDTO;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.service.SessionService;

import javax.inject.Inject;

@NoArgsConstructor
public class UserEndSessionCommand extends AbstractCommand {

    @Inject
    @NotNull
    private SessionService sessionService;

    @Inject
    @NotNull
    private UserEndpointBean userEndpointBean;

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
        return "user-logout";
    }

    @Override
    public String description() {
        return "Log out user";
    }

    @Override
    public void execute() {
        SessionDTO session = sessionService.getSession();
        System.out.println(userEndpointBean.findUserById(session.getParentId()).getLogin() + " was logged out");
        sessionService.setSession(null);
    }
}

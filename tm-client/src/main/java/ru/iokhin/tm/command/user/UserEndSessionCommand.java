package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.Session;
import ru.iokhin.tm.endpoint.UserEndpointBean;

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
        Session session = endpointServiceLocator.getSession();
        UserEndpointBean userEndpointBean = endpointServiceLocator.getUserEndpointBean();
        System.out.println(userEndpointBean.findById(session.getParentId()).getLogin() + " was logged out");
        endpointServiceLocator.setSession(null);
    }
}

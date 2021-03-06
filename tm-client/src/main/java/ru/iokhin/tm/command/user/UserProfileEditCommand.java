package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserDTO;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

@Component
@NoArgsConstructor
public class UserProfileEditCommand extends AbstractCommand {

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
        @Nullable UserDTO currentUser = userEndpointBean.findUserById(sessionService.getSession().getParentId());
        System.out.println("USER PROFILE:");
        System.out.println("USER ID: " + currentUser.getId());
        System.out.println("USER LOGIN: " + currentUser.getLogin());
        System.out.println("USER RIGHTS: " + currentUser.getRoleType());
        System.out.println("ENTER NEW LOGIN TO EDIT");
        @NotNull final String login = terminalService.nextLine();
        System.out.println("ENTER NEW LOGIN TO EDIT");
        @NotNull final String password = terminalService.nextLine();
        userEndpointBean.editUser(sessionService.getSession(), login, password);
        System.out.println("SUCCESS");
    }
}
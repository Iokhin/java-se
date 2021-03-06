package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.service.TerminalService;

@Component
@NoArgsConstructor
public class UserPasswordChangeCommand extends AbstractCommand {

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
        return "user-password-change";
    }

    @Override
    public String description() {
        return "Change password for current user";
    }

    @Override
    public void execute() {
        System.out.println("ENTER THE CURRENT PASSWORD");
        @NotNull final String oldPassword = terminalService.nextLine();
        System.out.println("ENTER NEW PASSWORD");
        @NotNull final String newPassword = terminalService.nextLine();
        if (!userEndpointBean.passChange(oldPassword, newPassword)) {
            System.out.println("WRONG PASSWORD, TRY AGAIN");
            return;
        }
        System.out.println("SUCCESS");
    }

}
package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public class UserRegistrationCommand extends AbstractCommand {

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
        return "user-reg";
    }

    @Override
    public String description() {
        return "Register new user";
    }

    @Override
    public void execute() {
        System.out.println("ENTER NEW USER'S LOGIN");
        @NotNull final String login = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getUserEndpointBean().findByLogin(login) != null) {
            System.out.println("SUCH LOGIN ALREADY EXIST");
            return;
        }
        System.out.println("ENTER NEW USER'S PASSWORD");
        @NotNull final String password = endpointServiceLocator.getTerminalService().nextLine();
        endpointServiceLocator.getUserEndpointBean().addUser(login, password);
        System.out.println("SUCCESS");
    }
}

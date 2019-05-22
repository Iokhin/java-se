package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.entity.User;

@NoArgsConstructor
public class UserRegistrationCommand extends AbstractCommand {

    @Override
    public boolean security() {
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
        @NotNull final String login = bootstrap.getTerminalService().nextLine();
        for (@NotNull User user : bootstrap.getUserService().findAll()) {
            if (user.getLogin().equals(login)) {
                System.out.println("SUCH LOGIN ALREADY EXIST");
                return;
            }
        }
        System.out.println("ENTER NEW USER'S PASSWORD");
        @NotNull final String password = bootstrap.getTerminalService().nextLine();
        bootstrap.getUserService().add(RoleType.USER, login, password);
        System.out.println("SUCCESS");
    }
}

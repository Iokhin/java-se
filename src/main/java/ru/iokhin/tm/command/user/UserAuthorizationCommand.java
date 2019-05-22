package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.User;

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
    public void execute() {
        System.out.println("PLEASE ENTER YOUR LOGIN");
        @NotNull final String login = bootstrap.getTerminalService().nextLine();
        @Nullable final User user = bootstrap.getUserService().findByLogin(login);
        if (user == null) {
            System.out.println("INCORRECT LOGIN");
            return;
        }
        System.out.println("PLEASE ENTER YOUR PASSWORD");
        @NotNull final String password = bootstrap.getTerminalService().nextLine();
        @NotNull final String passwordHash = MD5Util.passwordToHash(password);
        if (user.getPasswordHash().equals(passwordHash)) {
            bootstrap.setCurrentUser(user);
            System.out.println("WELCOME, " + user.getLogin());
        } else System.out.println("INCORRECT PASSWORD");
    }
}

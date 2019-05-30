package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.User;

import javax.xml.soap.SOAPException;

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
    public void execute() throws AuthException, SOAPException {
        System.out.println("PLEASE ENTER YOUR LOGIN");
        @NotNull final String login = serviceLocator.getTerminalService().nextLine();
        System.out.println("PLEASE ENTER YOUR PASSWORD");
        @NotNull final String password = serviceLocator.getTerminalService().nextLine();
        User user = null;
        user = serviceLocator.getUserService().authUser(login, password);
        serviceLocator.getUserService().setCurrentUser(user);
        System.out.println("WELCOME, " + user.getLogin());

    }
}
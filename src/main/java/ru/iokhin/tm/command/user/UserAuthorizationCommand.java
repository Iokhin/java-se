package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.User;

import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        String login = scanner.nextLine();

        User user = bootstrap.getUserService().getUserByLogin(login);
        if (user == null) {
            System.out.println("INCORRECT LOGIN");
            return;
        }

        System.out.println("PLEASE ENTER YOUR PASSWORD");
        String password = scanner.nextLine();

        String passwordHash = MD5Util.passwordToHash(password);

        if (user.getPasswordHash().equals(passwordHash)) {
            bootstrap.setCurrentUser(user);
            System.out.println("WELCOME, " + user.getLogin());
        }
        else System.out.println("INCORRECT PASSWORD");
    }
}

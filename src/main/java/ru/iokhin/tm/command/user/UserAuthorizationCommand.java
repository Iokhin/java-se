package ru.iokhin.tm.command.user;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.User;

import java.util.Scanner;

public class UserAuthorizationCommand extends AbstractCommand {

    public UserAuthorizationCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        String input = scanner.nextLine();
        for (User user : bootstrap.userRepository.userMap.values()) {
            if (input.equals(user.getLogin())) {
                System.out.println("PLEASE ENTER YOUR PASSWORD");
                input = scanner.nextLine();
                if (MD5Util.passwordToHash(input).equals(user.getPasswordHash())) {
                    bootstrap.setCurrentUser(user);
                    System.out.println("WELCOME, " + user.getLogin());
                    return;
                }
            }
        }
        System.out.println("INCORRECT LOGIN OR PASSWORD, TRY AGAIN");
//        execute();
    }
}

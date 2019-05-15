package ru.iokhin.tm.command.userCommand;

import org.apache.commons.codec.digest.DigestUtils;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.User;

import java.util.Scanner;

public class UserAuthorizationCommand extends AbstractCommand {

    public UserAuthorizationCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
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
                if (DigestUtils.md5Hex(input).equals(user.getPasswordHash())) {
                    bootstrap.setCurrentUser(user);
                    System.out.println("WELCOME, " + user.getLogin());
                    return;
                }
            }
        }
        System.out.println("INCORRECT LOGIN OR PASSWORD, TRY AGAIN");
        execute();
    }
}

package ru.iokhin.tm.command.userCommand;

//import org.apache.commons.codec.digest.DigestUtils;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.MD5Util;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserPasswordChangeCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public UserPasswordChangeCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("ENTER THE CURRENT PASSWORD");
        String input = scanner.nextLine();
        if (bootstrap.getCurrentUser().getPasswordHash().equals(MD5Util.passwordToHash(input))) {
            System.out.println("ENTER NEW PASSWORD");
            input = scanner.nextLine();
            bootstrap.getCurrentUser().setPasswordHash(input);
            System.out.println("OK");
            return;
        }
        System.out.println("WRONG PASSWORD, TRY AGAIN");
    }

}
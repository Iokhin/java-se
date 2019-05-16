package ru.iokhin.tm.command.userCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserProfileEditCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public UserProfileEditCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("USER PROFILE:");
        System.out.println(bootstrap.getCurrentUser().getUserId());
        System.out.println(bootstrap.getCurrentUser().getLogin());
        System.out.println(bootstrap.getCurrentUser().getRoleType().displayName());
        System.out.println("ENTER NEW LOGIN TO EDIT");
        String input = scanner.nextLine();
        bootstrap.getCurrentUser().setLogin(input);
        System.out.println("SUCCESS");
    }
}
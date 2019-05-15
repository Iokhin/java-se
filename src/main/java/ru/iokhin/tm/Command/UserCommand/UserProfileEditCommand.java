package ru.iokhin.tm.Command.UserCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.Command.AbstractCommand;

import java.util.Scanner;

public class UserProfileEditCommand extends AbstractCommand {

    private static final String name = "user-edit";
    private static final String description = "user-edit: Edit the current user's profile";

    private Scanner scanner = new Scanner(System.in);

    public UserProfileEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
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

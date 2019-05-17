package ru.iokhin.tm.command.user;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class UserProfileEditCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public UserProfileEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "user-edit";
    }

    @Override
    public String description() {
        return "Edit current user's profile";
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
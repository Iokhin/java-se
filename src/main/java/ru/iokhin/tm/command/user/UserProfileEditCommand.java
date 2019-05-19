package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public class UserProfileEditCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

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
        System.out.println("USER ID: " + bootstrap.getCurrentUser().getUserId());
        System.out.println("USER LOGIN: " + bootstrap.getCurrentUser().getLogin());
        System.out.println("USER RIGHTS: " + bootstrap.getCurrentUser().getRoleType().displayName());
        System.out.println("ENTER NEW LOGIN TO EDIT");
        String input = scanner.nextLine();
        bootstrap.getCurrentUser().setLogin(input);
        System.out.println("SUCCESS");
    }
}
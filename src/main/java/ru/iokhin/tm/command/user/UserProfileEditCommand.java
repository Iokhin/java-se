package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public class UserProfileEditCommand extends AbstractCommand {

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
        System.out.println("USER ID: " + bootstrap.getCurrentUser().getId());
        System.out.println("USER LOGIN: " + bootstrap.getCurrentUser().getLogin());
        System.out.println("USER RIGHTS: " + bootstrap.getCurrentUser().getRoleType().displayName());
        System.out.println("ENTER NEW LOGIN TO EDIT");
        String input = bootstrap.getTerminalService().nextLine();
        bootstrap.getCurrentUser().setLogin(input);
        System.out.println("SUCCESS");
    }
}
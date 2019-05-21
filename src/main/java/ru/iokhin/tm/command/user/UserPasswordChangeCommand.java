package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.util.MD5Util;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public class UserPasswordChangeCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "user-password-change";
    }

    @Override
    public String description() {
        return "Change password for current user";
    }

    @Override
    public void execute() {
        System.out.println("ENTER THE CURRENT PASSWORD");
        String input = bootstrap.getTerminalService().nextLine();
        if (bootstrap.getCurrentUser().getPasswordHash().equals(MD5Util.passwordToHash(input))) {
            System.out.println("ENTER NEW PASSWORD");
            input = bootstrap.getTerminalService().nextLine();
            bootstrap.getCurrentUser().setPasswordHash(input);
            System.out.println("OK");
            return;
        }
        System.out.println("WRONG PASSWORD, TRY AGAIN");
    }

}
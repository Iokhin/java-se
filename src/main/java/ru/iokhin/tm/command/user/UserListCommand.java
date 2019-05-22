package ru.iokhin.tm.command.user;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public class UserListCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "user-list";
    }

    @Override
    public String description() {
        return "List all users";
    }

    @Override
    public void execute() {
        System.out.println("USERS LIST:");
        bootstrap.getUserService().findAll().forEach(System.out::println);
    }
}

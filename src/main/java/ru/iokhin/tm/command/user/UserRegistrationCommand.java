package ru.iokhin.tm.command.user;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.service.UserService;

import java.util.Scanner;

public class UserRegistrationCommand extends AbstractCommand {

    public UserRegistrationCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public UserRegistrationCommand() {

    }

    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return false;
    }

    @Override
    public String name() {
        return "user-reg";
    }

    @Override
    public String description() {
        return "Register new user";
    }

    @Override
    public void execute() {
        System.out.println("ENTER NEW USER'S LOGIN");
        String login = scanner.nextLine();
        for (User user : bootstrap.getUserService().getAllUsers().values()) {
            if (user.getLogin().equals(login)) {
                System.out.println("SUCH LOGIN ALREADY EXIST");
                execute();
                return;
            }
        }
        System.out.println("ENTER NEW USER'S PASSWORD");
        String password = scanner.nextLine();
        bootstrap.getUserService().addUser(RoleType.USER, login, password);
        System.out.println("SUCCESS");
    }
}

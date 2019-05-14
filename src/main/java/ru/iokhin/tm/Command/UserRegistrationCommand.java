package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.RoleType;
import ru.iokhin.tm.entity.User;

import java.util.Scanner;

public class UserRegistrationCommand extends AbstractCommand {

    private static final String name = "user-registration";
    private static final String description = "user-registration: Register a new user";

    private Scanner scanner = new Scanner(System.in);

    public UserRegistrationCommand(Bootstrap bootstrap) {
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
        System.out.println("ENTER NEW USER'S LOGIN");
        String login = scanner.nextLine();
        for (User user : bootstrap.userRepository.userMap.values()) {
            if (user.getLogin().equals(login)) {
                System.out.println("SUCH LOGIN ALREADY EXIST");
                execute();
                return;
            }
        }
        System.out.println("ENTER NEW USER'S PASSWORD");
        String password = scanner.nextLine();
        bootstrap.getUserService().createUser(RoleType.USER, login, password);
        System.out.println("SUCCESS");
    }
}

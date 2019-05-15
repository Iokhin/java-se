package ru.iokhin.tm.command.userCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.RoleType;
import ru.iokhin.tm.entity.User;

import java.util.Scanner;

public class UserRegistrationCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public UserRegistrationCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
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
        bootstrap.getUserService().addUser(RoleType.USER, login, password);
        System.out.println("SUCCESS");
    }
}

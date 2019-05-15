package ru.iokhin.tm.command.projectCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectEditCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public ProjectEditCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO EDIT");
        String idEdit = scanner.nextLine();
        System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");
        String newName = scanner.nextLine();
        projectEditCommand(idEdit, newName);
        System.out.println("OK");
    }

    private void projectEditCommand(String id, String newName) {
        bootstrap.getProjectService().editProject(id, newName);
    }
}

package ru.iokhin.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectCreateCommand extends AbstractCommand {

    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectCreateCommand() {

    }

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public final boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-create";
    }

    @Override
    public String description() {
        return "Creates project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER NAME OF PROJECT TO CREATE");

        @NotNull
        String name = scanner.nextLine();

        projectCreateCommand(name);
        System.out.println("OK");
    }


    private void projectCreateCommand(@NotNull String name) {
        bootstrap.getProjectService().addProject(name, bootstrap.getCurrentUser());
    }
}

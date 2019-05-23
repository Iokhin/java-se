package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class ProjectCreateCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-create";
    }

    @Override
    public String description() {
        return "Create project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER NAME OF PROJECT TO CREATE");
        @NotNull final String name = serviceLocator.getTerminalService().nextLine();
        projectCreateCommand(name);
        System.out.println("OK");
    }

    private void projectCreateCommand(@NotNull String name) {
        serviceLocator.getProjectService().add(serviceLocator.getUserService().getCurrentUser(), name);
    }
}

package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class ProjectRemoveCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove project by ID";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO REMOVE");
        @NotNull final String projectId = serviceLocator.getTerminalService().nextLine();
        if (serviceLocator.getProjectService().remove(serviceLocator.getUserService().getCurrentUser(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("OK");
    }
}

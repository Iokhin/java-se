package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class ProjectEditCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-edit";
    }

    @Override
    public String description() {
        return "Edit project by ID";
    }

    @Override
    public void execute() {
        bootstrap.getCommandMap().get("project-list").execute();
        System.out.println("ENTER ID OF PROJECT TO EDIT");
        @NotNull final String projectId = bootstrap.getTerminalService().nextLine();
        System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");
        @NotNull final String newName = bootstrap.getTerminalService().nextLine();
        if (bootstrap.getProjectService().edit(bootstrap.getCurrentUser(), projectId, newName) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("OK");
    }
}

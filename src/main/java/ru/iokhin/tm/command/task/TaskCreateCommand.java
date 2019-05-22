package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class TaskCreateCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-create";
    }

    @Override
    public String description() {
        return "Create task for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CREATE TASK");
        bootstrap.getCommandMap().get("project-list").execute();
        @NotNull final String projectId = bootstrap.getTerminalService().nextLine();
        if (bootstrap.getProjectService().findOne(bootstrap.getCurrentUser(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("ENTER NAME OF TASK TO CREATE");
        @NotNull final String taskName = bootstrap.getTerminalService().nextLine();
        bootstrap.getTaskService().add(bootstrap.getCurrentUser(), projectId, taskName);
        System.out.println("OK");
    }
}


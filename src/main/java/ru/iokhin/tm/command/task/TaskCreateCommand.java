package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class TaskCreateCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    private boolean isHaveAccess(Bootstrap bootstrap, String projectId) {

        @NotNull final String currentUserId = bootstrap.getCurrentUser().getId();
        @NotNull final String allowedUserId = bootstrap.getProjectService().findOne(projectId).getUserId();

        return currentUserId.equals(allowedUserId);
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
        String projectId = bootstrap.getTerminalService().nextLine();
        if (bootstrap.getProjectService().findOne(projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        if (!isHaveAccess(bootstrap, projectId)) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("ENTER NAME OF TASK TO CREATE");
        String taskName = bootstrap.getTerminalService().nextLine();
        bootstrap.getTaskService().add(bootstrap.getCurrentUser().getId(), projectId, taskName);
        System.out.println("OK");
    }
}


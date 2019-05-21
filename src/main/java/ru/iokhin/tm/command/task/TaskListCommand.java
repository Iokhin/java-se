package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Task;

import java.util.Collection;

@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {

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
        return "task-list";
    }

    @Override
    public String description() {
        return "List all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");

        @NotNull
        String projectId = bootstrap.getTerminalService().nextLine();
        if (bootstrap.getProjectService().findOne(projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }

        assert bootstrap.getCurrentUser() != null;
        if (!isHaveAccess(bootstrap, projectId)) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("TASKS LIST:");
        int i = 0;
        for (Task task : getTaskList(projectId)) {
            System.out.println(++i + ". " + task);
        }
    }

    private Collection<Task> getTaskList(String projectId) {
        return bootstrap.getTaskService().findAllByProjectId(projectId);
    }
}

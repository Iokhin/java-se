package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Task;

import java.util.Collection;

@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
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
        @NotNull final String projectId = serviceLocator.getTerminalService().nextLine();
        if (serviceLocator.getProjectService().findOne(serviceLocator.getUserService().getCurrentUser(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("TASKS LIST:");
        int i = 0;
        for (@NotNull Task task : getTaskList(projectId)) {
            System.out.println(++i + ". " + task);
        }
    }

    private Collection<Task> getTaskList(String projectId) {
        return serviceLocator.getTaskService().findAllByProjectId(serviceLocator.getUserService().getCurrentUser(), projectId);
    }
}

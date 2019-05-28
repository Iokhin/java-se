package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class TaskRemoveAllByProjectCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-clear";
    }

    @Override
    public String description() {
        return "Remove all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");
        @NotNull final String projectId = serviceLocator.getTerminalService().nextLine();
        if (!serviceLocator.getTaskService().removeAllByProjectId(serviceLocator.getUserService().getCurrentUser().getId(), projectId)) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        serviceLocator.getTaskService().removeAllByProjectId(serviceLocator.getUserService().getCurrentUser().getId(), projectId);
        System.out.println("OK");
    }
}

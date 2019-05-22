package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class TaskEditCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-edit";
    }

    @Override
    public String description() {
        return "Edit task by ID";
    }

    @Override
    public void execute() {
        bootstrap.getCommandMap().get("task-list-all").execute();
        System.out.println("ENTER ID OF TASK TO EDIT");
        @NotNull final String taskId = bootstrap.getTerminalService().nextLine();
        System.out.println("ENTER NEW NAME OF TASK TO EDIT");
        @NotNull final String newTaskName = bootstrap.getTerminalService().nextLine();
        if (bootstrap.getTaskService().edit(bootstrap.getCurrentUser(), taskId, newTaskName) == null) {
            System.out.println("NO SUCH TASK ID");
            return;
        }
        System.out.println("OK");
    }
}


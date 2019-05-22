package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class TaskRemoveCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-remove";
    }

    @Override
    public String description() {
        return "Remove task by ID";
    }

    @Override
    public void execute() {
        bootstrap.getCommandMap().get("task-list-all").execute();
        System.out.println("ENTER ID OF TASk TO REMOVE");
        @NotNull final String taskId = bootstrap.getTerminalService().nextLine();
        if (bootstrap.getTaskService().remove(bootstrap.getCurrentUser(), taskId) == null) {
            System.out.println("NO SUCH TASK ID");
            return;
        }
        System.out.println("OK");
    }
}


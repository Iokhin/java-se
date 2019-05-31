package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;

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
    public void execute() throws AuthException_Exception {
        System.out.println("ENTER ID OF TASk TO REMOVE");
        @NotNull final String taskId = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getTaskEndpointBean().removeTask(endpointServiceLocator.getSession(), taskId) == null) {
            System.out.println("NO SUCH TASK ID");
            return;
        }
        System.out.println("OK");
    }
}


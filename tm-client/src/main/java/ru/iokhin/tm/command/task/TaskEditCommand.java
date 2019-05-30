package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;
import ru.iokhin.tm.endpoint.Status;

@NoArgsConstructor
public final class TaskEditCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-editUser";
    }

    @Override
    public String description() {
        return "Edit task by ID";
    }

    @Override
    public void execute() throws AuthException_Exception {
        System.out.println("ENTER ID OF TASK TO EDIT");
        @NotNull final String taskId = endpointServiceLocator.getTerminalService().nextLine();
        System.out.println("ENTER NEW NAME OF TASK TO EDIT");
        @NotNull final String newTaskName = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getTaskEndpointBean().edit(endpointServiceLocator.getSession(), taskId, newTaskName, Status.PROCCESSING) == null) {
            System.out.println("NO SUCH TASK ID");
            return;
        }
        System.out.println("OK");
    }
}


package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;

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
    public void execute() throws AuthException_Exception {
        System.out.println("ENTER ID OF PROJECT TO CREATE TASK");
        @NotNull final String projectId = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getProjectEndpointBean().findOne(endpointServiceLocator.getSession(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("ENTER NAME OF TASK TO CREATE");
        @NotNull final String taskName = endpointServiceLocator.getTerminalService().nextLine();
        endpointServiceLocator.getTaskEndpointBean().addTask(endpointServiceLocator.getSession(), projectId, taskName);
        System.out.println("OK");
    }
}


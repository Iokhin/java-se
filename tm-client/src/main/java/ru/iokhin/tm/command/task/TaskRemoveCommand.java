package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import javax.inject.Inject;

@NoArgsConstructor
public final class TaskRemoveCommand extends AbstractCommand {

    @Inject
    @NotNull
    private TaskEndpointBean taskEndpointBean;

    @Inject
    @NotNull
    private SessionService sessionService;

    @Inject
    @NotNull
    private TerminalService terminalService;

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public boolean admin() {
        return false;
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
        System.out.println("ENTER ID OF TASK TO REMOVE");
        @NotNull final String taskId = terminalService.nextLine();
        if (taskEndpointBean.removeTask(sessionService.getSession(), taskId) == null) {
            System.out.println("NO SUCH TASK ID");
            return;
        }
        System.out.println("OK");
    }
}


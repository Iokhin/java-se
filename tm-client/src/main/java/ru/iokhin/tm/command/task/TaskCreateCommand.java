package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

@Component
@NoArgsConstructor
public final class TaskCreateCommand extends AbstractCommand {

    @Autowired
    @NotNull
    private ProjectEndpointBean projectEndpointBean;

    @Autowired
    @NotNull
    private TaskEndpointBean taskEndpointBean;

    @Autowired
    @NotNull
    private SessionService sessionService;

    @Autowired
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
        return "task-create";
    }

    @Override
    public String description() {
        return "Create task for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CREATE TASK");
        @NotNull final String projectId = terminalService.nextLine();
        if (projectEndpointBean.findProject(sessionService.getSession(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("ENTER NAME OF TASK TO CREATE");
        @NotNull final String taskName = terminalService.nextLine();
        taskEndpointBean.addTask(sessionService.getSession(), projectId, taskName);
        System.out.println("OK");
    }
}


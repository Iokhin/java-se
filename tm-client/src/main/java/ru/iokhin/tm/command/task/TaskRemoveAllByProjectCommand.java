package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

@Component
@NoArgsConstructor
public final class TaskRemoveAllByProjectCommand extends AbstractCommand {

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
        return "task-clear";
    }

    @Override
    public String description() {
        return "Remove all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");
        @NotNull final String projectId = terminalService.nextLine();
        taskEndpointBean.removeAllByProjectId(sessionService.getSession(), projectId);
        System.out.println("OK");
    }
}

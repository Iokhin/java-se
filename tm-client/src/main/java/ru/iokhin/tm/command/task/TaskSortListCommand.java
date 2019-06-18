package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.endpoint.TaskDTO;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import javax.inject.Inject;
import java.util.List;

public class TaskSortListCommand extends AbstractCommand {

    @Inject
    @NotNull
    private ProjectEndpointBean projectEndpointBean;

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
        return "task-sort-list";
    }

    @Override
    public String description() {
        return "List tasks sorted by one of this options: order, dateStart, dateEnd, status";
    }

    @Override
    public void execute() {
        System.out.println("CHOSE ONE OF THIS OPTIONS TO SORT: order, dateStart, dateEnd, status");
        @NotNull final String option = terminalService.nextLine();
        List<TaskDTO> sorted = taskEndpointBean.sortTaskByUserId(sessionService.getSession(), option);
        if (sorted == null) {
            System.out.println("WRONG OPTION");
            return;
        }
        int i = 0;
        for (TaskDTO taskDTO : sorted) {
            System.out.println(++i + ". " + taskDTO.getName() + ", " + taskDTO.getId());
        }
    }
}

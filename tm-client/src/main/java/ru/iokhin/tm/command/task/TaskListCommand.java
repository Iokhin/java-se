package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.endpoint.TaskDTO;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import java.util.List;

@Component
@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {

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
        return "task-list";
    }

    @Override
    public String description() {
        return "List all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");
        @NotNull final String projectId = terminalService.nextLine();
        if (projectEndpointBean.findProject(sessionService.getSession(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("TASKS LIST:");
        int i = 0;
        for (@NotNull TaskDTO task : getTaskList(projectId)) {
            System.out.println(++i + ". " + task.getName() + ", " + task.getId());
        }
    }

    private List<TaskDTO> getTaskList(String projectId) {
        return taskEndpointBean.findAllTaskByProjectId(sessionService.getSession(), projectId);
    }
}

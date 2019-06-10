package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.TaskDTO;

import java.util.List;

@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {

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
        @NotNull final String projectId = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getProjectEndpointBean().findProject(endpointServiceLocator.getSession(), projectId) == null) {
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
        return endpointServiceLocator.getTaskEndpointBean().findAllTaskByProjectId(endpointServiceLocator.getSession(), projectId);
    }
}

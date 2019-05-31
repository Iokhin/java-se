package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;
import ru.iokhin.tm.endpoint.Task;

import java.util.Collection;

@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
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
    public void execute() throws AuthException_Exception {
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");
        @NotNull final String projectId = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getProjectEndpointBean().findOne(endpointServiceLocator.getSession(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("TASKS LIST:");
        int i = 0;
        for (@NotNull Task task : getTaskList(projectId)) {
            System.out.println(++i + ". " + task);
        }
    }

    private Collection<Task> getTaskList(String projectId) throws AuthException_Exception {
        return endpointServiceLocator.getTaskEndpointBean().findAllTaskByProjectId(endpointServiceLocator.getSession(), projectId);
    }
}

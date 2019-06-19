package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.TaskDTO;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.service.SessionService;

import java.util.List;

@Component
public class TaskListAllCommand extends AbstractCommand {

    @Autowired
    @NotNull
    private TaskEndpointBean taskEndpointBean;

    @Autowired
    @NotNull
    private SessionService sessionService;

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
        return "task-list-all";
    }

    @Override
    public String description() {
        return "List all tasks for current user";
    }

    @Override
    public void execute() {
        System.out.println("TASK LIST:");
        List<TaskDTO> taskCollection = taskEndpointBean.findAllTaskByUserId(sessionService.getSession());
        if (taskCollection == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        int i = 0;
        for (TaskDTO task : taskCollection) {
            System.out.println(++i + ". " + task.getName() + ", " + task.getId());
        }
    }
}

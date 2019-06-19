package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.endpoint.UserDTO;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.service.SessionService;

@Component
public class TaskRemoveAllByUserCommand extends AbstractCommand {

    @Autowired
    @NotNull
    private TaskEndpointBean taskEndpointBean;

    @Autowired
    @NotNull
    private SessionService sessionService;

    @Autowired
    @NotNull
    private UserEndpointBean userEndpointBean;

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
        return "task-clear-all";
    }

    @Override
    public String description() {
        return "Remove all tasks for current user";
    }

    @Override
    public void execute() {
        taskEndpointBean.removeAllTaskByUserId(sessionService.getSession());
        UserDTO user = userEndpointBean.findUserById(sessionService.getSession().getParentId());
        System.out.println("ALL TASKS FOR " + user.getLogin() + " WAS REMOVED");
    }
}

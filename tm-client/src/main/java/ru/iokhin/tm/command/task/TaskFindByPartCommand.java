package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.TaskDTO;
import ru.iokhin.tm.endpoint.TaskEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import java.util.Collection;

@Component
public class TaskFindByPartCommand extends AbstractCommand {

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
        return "task-find";
    }

    @Override
    public String description() {
        return "Find tasks by part of name or description and list them";
    }

    @Override
    public void execute() {
        System.out.println("ENTER KEY WORD TO FIND");
        @NotNull final String keyWord = terminalService.nextLine();
        Collection<TaskDTO> found = taskEndpointBean.findTaskByPartOfNameOrDescription(sessionService.getSession(), keyWord);
        if (found.size() == 0) {
            System.out.println("NO RESULTS FOUND FOR " + keyWord);
            return;
        }
        found.forEach(System.out::println);
    }
}

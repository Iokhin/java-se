package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.Task;

import java.util.Collection;

public class TaskFindByPartCommand extends AbstractCommand {
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
        @NotNull final String keyWord = endpointServiceLocator.getTerminalService().nextLine();
        Collection<Task> found = endpointServiceLocator.getTaskEndpointBean().findTaskByPartOfNameOrDescription(endpointServiceLocator.getSession(), keyWord);
        if (found.size() == 0) {
            System.out.println("NO RESULTS FOUND FOR " + keyWord);
            return;
        }
        found.forEach(System.out::println);
    }
}

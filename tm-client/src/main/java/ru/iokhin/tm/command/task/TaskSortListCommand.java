package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.Task;

import java.util.Collection;

public class TaskSortListCommand extends AbstractCommand {
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
        @NotNull final String option = endpointServiceLocator.getTerminalService().nextLine();
        Collection<Task> sorted = endpointServiceLocator.getTaskEndpointBean().sortTaskByUserId(endpointServiceLocator.getSession(), option);
        if (sorted == null) {
            System.out.println("WRONG OPTION");
            return;
        }
        sorted.forEach(System.out::println);
    }
}

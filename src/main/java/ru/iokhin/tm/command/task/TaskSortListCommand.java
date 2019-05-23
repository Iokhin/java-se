package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Task;

import java.util.Collection;

public class TaskSortListCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
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
        @NotNull final String option = serviceLocator.getTerminalService().nextLine();
        Collection<Task> sorted = serviceLocator.getTaskService().sortByUserId(serviceLocator.getUserService().getCurrentUser().getId(), option);
        if (sorted == null) {
            System.out.println("WRONG OPTION");
            return;
        }
        sorted.forEach(System.out::println);
    }
}

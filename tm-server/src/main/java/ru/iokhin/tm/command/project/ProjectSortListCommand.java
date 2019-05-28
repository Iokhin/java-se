package ru.iokhin.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Project;

import java.util.Collection;

public class ProjectSortListCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-sort-list";
    }

    @Override
    public String description() {
        return "List projects sorted by one of this options: order, dateStart, dateEnd, status";
    }

    @Override
    public void execute() {
        System.out.println("CHOSE ONE OF THIS OPTIONS TO SORT: order, dateStart, dateEnd, status");
        @NotNull final String option = serviceLocator.getTerminalService().nextLine();
        Collection<Project> sorted = serviceLocator.getProjectService().sortByUserId(serviceLocator.getUserService().getCurrentUser().getId(), option);
        if (sorted == null) {
            System.out.println("WRONG OPTION");
            return;
        }
        sorted.forEach(System.out::println);
    }
}

package ru.iokhin.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Project;

import java.util.Collection;
import java.util.List;

public class ProjectFindByPartCommand extends AbstractCommand {
    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-find";
    }

    @Override
    public String description() {
        return "Find projects by part of name or description and list them";
    }

    @Override
    public void execute() {
        System.out.println("ENTER KEY WORD TO FIND");
        @NotNull final String keyWord = bootstrap.getTerminalService().nextLine();
        Collection<Project> found = bootstrap.getProjectService().findByPartOfNameOrDescription(bootstrap.getCurrentUser().getId(), keyWord);
        found.forEach(System.out::println);
    }
}

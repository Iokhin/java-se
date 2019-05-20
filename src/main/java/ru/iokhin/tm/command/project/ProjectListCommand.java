package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Project;

import java.util.Collection;

@NoArgsConstructor
public final class ProjectListCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-list";
    }

    @Override
    public String description() {
        return "List all projects for current user";
    }

    @Override
    public void execute() {
        System.out.println("PROJECTS LIST:");
        int i = 0;
        for (Project project : projectList()) {
            System.out.println(++i + ". " + project);
        }
    }


    private Collection<Project> projectList() {
        assert bootstrap.getCurrentUser() != null;
        return bootstrap.getProjectService().findAllByUserId(bootstrap.getCurrentUser().getId());
    }
}

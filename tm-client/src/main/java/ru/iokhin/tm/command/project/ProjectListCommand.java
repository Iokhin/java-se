package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectDTO;

import java.util.Collection;

@NoArgsConstructor
public final class ProjectListCommand extends AbstractCommand {

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
        return "project-list";
    }

    @Override
    public String description() {
        return "List all projects for current user";
    }

    @Override
    public void execute() {
        System.out.println("PROJECTS LIST:");
        @NotNull int i = 0;
        for (@NotNull ProjectDTO project : projectList()) {
            System.out.println(++i + ". " + project.getName() + ", " + project.getId());
        }
    }

    private Collection<ProjectDTO> projectList() {
        return endpointServiceLocator.getProjectEndpointBean().findAllProjectByUserId(endpointServiceLocator.getSession());
    }
}

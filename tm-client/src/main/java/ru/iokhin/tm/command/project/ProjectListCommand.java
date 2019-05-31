package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;
import ru.iokhin.tm.endpoint.Project;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;

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
    public void execute() throws AuthException_Exception {
        System.out.println("PROJECTS LIST:");
        @NotNull int i = 0;
        for (@NotNull Project project : projectList()) {
            System.out.println(++i + ". " + project);
        }
    }

    private Collection<Project> projectList() throws AuthException_Exception {
        return endpointServiceLocator.getProjectEndpointBean().findAllByUserId(endpointServiceLocator.getSession());
    }
}

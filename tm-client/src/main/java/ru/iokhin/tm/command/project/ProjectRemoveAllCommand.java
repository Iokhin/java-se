package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.service.SessionService;

@Component
@NoArgsConstructor
public final class ProjectRemoveAllCommand extends AbstractCommand {

    @Autowired
    @NotNull
    private ProjectEndpointBean projectEndpointBean;

    @Autowired
    @NotNull
    private SessionService sessionService;

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
        return "project-clear";
    }

    @Override
    public String description() {
        return "Remove all projects for current user";
    }

    @Override
    public void execute() {
        projectEndpointBean.removeAllProjectByUserId(sessionService.getSession());
        System.out.println("OK");
    }
}

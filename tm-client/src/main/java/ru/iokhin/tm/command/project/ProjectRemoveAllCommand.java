package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;

@NoArgsConstructor
public final class ProjectRemoveAllCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
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
    public void execute() throws AuthException_Exception {
        endpointServiceLocator.getProjectEndpointBean().removeAllByUserId(endpointServiceLocator.getSession());
        System.out.println("OK");
    }
}

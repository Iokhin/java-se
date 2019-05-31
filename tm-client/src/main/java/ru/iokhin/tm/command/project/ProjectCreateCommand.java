package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;

@NoArgsConstructor
public final class ProjectCreateCommand extends AbstractCommand {

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
        return "project-create";
    }

    @Override
    public String description() {
        return "Create project";
    }

    @Override
    public void execute() throws AuthException_Exception {
        if (endpointServiceLocator.getSession() == null) throw new AuthException_Exception();
        System.out.println("ENTER NAME OF PROJECT TO CREATE");
        @NotNull final String name = endpointServiceLocator.getTerminalService().nextLine();
        projectCreateCommand(name);
        System.out.println("OK");
    }

    private void projectCreateCommand(@NotNull String name) throws AuthException_Exception {
        endpointServiceLocator.getProjectEndpointBean().add(endpointServiceLocator.getSession(), name);
    }
}

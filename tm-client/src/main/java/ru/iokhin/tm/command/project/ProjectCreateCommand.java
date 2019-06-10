package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.exception.ClientAuthException;

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
    @SneakyThrows
    public void execute() {
        if (endpointServiceLocator.getSession() == null) throw new ClientAuthException();
        System.out.println("ENTER NAME OF PROJECT TO CREATE");
        @NotNull final String name = endpointServiceLocator.getTerminalService().nextLine();
        projectCreateCommand(name);
        System.out.println("OK");
    }

    private void projectCreateCommand(@NotNull String name) {
        endpointServiceLocator.getProjectEndpointBean().addProject(endpointServiceLocator.getSession(), name);
    }
}

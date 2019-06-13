package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.exception.ClientAuthException;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import javax.inject.Inject;

@NoArgsConstructor
public final class ProjectCreateCommand extends AbstractCommand {

    @Inject
    @NotNull
    private ProjectEndpointBean projectEndpointBean;

    @Inject
    @NotNull
    private SessionService sessionService;

    @Inject
    @NotNull
    private TerminalService terminalService;

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
        if (sessionService.getSession() == null) throw new ClientAuthException();
        System.out.println("ENTER NAME OF PROJECT TO CREATE");
        @NotNull final String name = terminalService.nextLine();
        projectCreateCommand(name);
        System.out.println("OK");
    }

    private void projectCreateCommand(@NotNull String name) {
        projectEndpointBean.addProject(sessionService.getSession(), name);
    }
}

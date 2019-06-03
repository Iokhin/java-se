package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class ProjectRemoveCommand extends AbstractCommand {

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
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove project by ID";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO REMOVE");
        @NotNull final String projectId = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getProjectEndpointBean().removeProject(endpointServiceLocator.getSession(), projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("OK");
    }
}

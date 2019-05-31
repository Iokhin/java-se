package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.AuthException_Exception;
import ru.iokhin.tm.endpoint.Status;

@NoArgsConstructor
public final class ProjectEditCommand extends AbstractCommand {

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
        return "project-edit";
    }

    @Override
    public String description() {
        return "Edit project by ID";
    }

    @Override
    public void execute() throws AuthException_Exception {
        System.out.println("ENTER ID OF PROJECT TO EDIT");
        @NotNull final String projectId = endpointServiceLocator.getTerminalService().nextLine();
        System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");
        @NotNull final String newName = endpointServiceLocator.getTerminalService().nextLine();
        if (endpointServiceLocator.getProjectEndpointBean().edit(endpointServiceLocator.getSession(), projectId, newName) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        System.out.println("OK");
    }
}

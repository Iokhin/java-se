package ru.iokhin.tm.command.project;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.ProjectDTO;
import ru.iokhin.tm.endpoint.ProjectEndpointBean;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import java.util.Collection;

@Component
public class ProjectSortListCommand extends AbstractCommand {

    @Autowired
    @NotNull
    private ProjectEndpointBean projectEndpointBean;

    @Autowired
    @NotNull
    private SessionService sessionService;

    @Autowired
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
        return "project-sort-list";
    }

    @Override
    public String description() {
        return "List projects sorted by one of this options: order, dateStart, dateEnd, status";
    }

    @Override
    public void execute() {
        System.out.println("CHOSE ONE OF THIS OPTIONS TO SORT: order, dateStart, dateEnd, status");
        @NotNull final String option = terminalService.nextLine();
        Collection<ProjectDTO> sorted = projectEndpointBean.sortProjectByUserId(sessionService.getSession(), option);
        if (sorted == null) {
            System.out.println("WRONG OPTION");
            return;
        }
        int i = 0;
        for (ProjectDTO projectDTO : sorted) {
            System.out.println(++i + ". " + projectDTO.getName() + ", " + projectDTO.getId());
        }
    }
}

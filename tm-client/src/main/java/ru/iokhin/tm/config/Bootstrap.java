package ru.iokhin.tm.config;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.RoleType;
import ru.iokhin.tm.endpoint.UserEndpointBean;
import ru.iokhin.tm.exception.ClientAuthException;
import ru.iokhin.tm.exception.ClientNonexistentCommandException;
import ru.iokhin.tm.service.SessionService;
import ru.iokhin.tm.service.TerminalService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;

@Getter
@ApplicationScoped
public class Bootstrap {

    @Inject
    @NotNull
    private SessionService sessionService;

    @Inject
    @NotNull
    private UserEndpointBean userEndpointBean;

    @Inject
    @NotNull
    private TerminalService terminalService;

    @Inject
    @NotNull
    private Map<String, AbstractCommand> commandMap;


    public void init() {
        System.out.println("***WELCOME TO TM-CLIENT***");
        @NotNull String input = "";
        while (!input.equals("exit")) {
            input = terminalService.nextLine();
            AbstractCommand command = this.commandMap.get(input);
            try {
                execute(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    private void execute(@Nullable AbstractCommand command) {
        if (command == null) throw new ClientNonexistentCommandException();
        if (command.security() && sessionService.getSession() == null) throw new ClientAuthException();
        if (command.admin() &&
                userEndpointBean.findUserById(sessionService.getSession().getParentId()).getRoleType() != RoleType.ADMIN)
            throw new ClientAuthException("THIS COMMAND ALLOWS ONLY FOR ADMIN");
        command.execute();
    }
}


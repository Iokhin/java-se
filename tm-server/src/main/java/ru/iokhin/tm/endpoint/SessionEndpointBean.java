package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.api.endpoint.SessionEndpoint;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
public class SessionEndpointBean implements SessionEndpoint {

    private ISessionService sessionService;

    public SessionEndpointBean(IServiceLocator serviceLocator) {
        sessionService = serviceLocator.getSessionService();
    }

    @Override
    @SneakyThrows
    public SessionDTO create(@WebParam(name = "userId") @NotNull String userId) {
        return sessionService.create(userId);
    }

    @Override
    @SneakyThrows
    public void validate(@WebParam(name = "session") @Nullable SessionDTO session) {
        sessionService.validate(session);
    }
}

package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.endpoint.SessionEndpoint;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;

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
    public Session create(@WebParam(name = "userId") @NotNull String userId) {
        return sessionService.create(userId);
    }

    @Override
    public void validate(@WebParam(name = "session") @Nullable Session session) throws AuthException {
        sessionService.validate(session);
    }
}

package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;

@WebService
public interface SessionEndpoint {

    Session create(@WebParam(name = "userId") @NotNull String userId) throws SQLException;

    void validate(@WebParam(name = "session") @Nullable Session session) throws AuthException;

}

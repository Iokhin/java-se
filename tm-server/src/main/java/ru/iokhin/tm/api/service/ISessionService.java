package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;

import java.io.IOException;

public interface ISessionService extends IService<Session> {

    Session create(@NotNull final String userId);

    void validate(@Nullable final Session session) throws AuthException;

    String sign(@NotNull Session session);

}

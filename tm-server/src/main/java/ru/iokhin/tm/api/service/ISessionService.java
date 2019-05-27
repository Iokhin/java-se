package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.exeption.AuthException;

public interface ISessionService extends IService<Session> {

    Session create(@NotNull final String userId);

    void validate(@Nullable final Session session) throws AuthException;

    boolean validateAdmin(@Nullable final Session session);

}

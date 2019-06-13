package ru.iokhin.tm.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.endpoint.SessionDTO;

import javax.enterprise.context.ApplicationScoped;

@Getter
@Setter
@NoArgsConstructor
@ApplicationScoped
public class SessionService {

    @Nullable
    private SessionDTO session;

}

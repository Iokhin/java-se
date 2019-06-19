package ru.iokhin.tm.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.endpoint.SessionDTO;

@Getter
@Setter
@NoArgsConstructor
@Component
@Scope("singleton")
public class SessionService {

    @Nullable
    private SessionDTO session;

}

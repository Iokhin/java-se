package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.User;

public interface IUserRepository {

    void add(User user);
    void list();
    void merge(User user);
    void delete(String id);
    void clear();

}

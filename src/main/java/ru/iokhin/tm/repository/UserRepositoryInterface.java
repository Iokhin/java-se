package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.User;

public interface UserRepositoryInterface {

    void add(User user);
    void list();
    void merge(User user);
    void delete(String id);
    void clear();

}

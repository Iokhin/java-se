package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.User;

public interface UserRepositoryInterface {

    public void add(User user);
    public void list();
    public void merge(User user);
    public void delete(String id);
    public void clear();

}

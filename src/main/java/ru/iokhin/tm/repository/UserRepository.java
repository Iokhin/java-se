package ru.iokhin.tm.repository;

import ru.iokhin.tm.api.IUserRepository;
import ru.iokhin.tm.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements IUserRepository {

    public Map<String, User> userMap = new HashMap<>(0);

    @Override
    public void add(User user) {
        userMap.put(user.getUserId(), user);
    }

    @Override
    public void list() {
        int i = 0;
        for (User user : userMap.values()) {
            System.out.println(++i + ". " + user.getLogin() + ", " + user.getUserId());
        }
    }

    @Override
    public void merge(User user) {
        if (user == null) return;
        userMap.put(user.getUserId(), user);
    }

    @Override
    public void delete(String id) {
        userMap.remove(id);
    }

    @Override
    public void clear() {
        userMap.clear();
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }
}
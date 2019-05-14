package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements UserRepositoryInterface {

    public Map<String, User> userMap = new HashMap<>(0);

    public User getUserRepositoryItem(String id) {
        return userMap.get(id);
    }

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
        userMap.merge(user.getUserId(), user, (oldVal, newVal) -> new User(newVal.getRoleType(), newVal.getLogin(),
                newVal.getPasswordHash(), oldVal.getUserId()));
    }

    @Override
    public void delete(String id) {
        userMap.remove(id);
    }

    @Override
    public void clear() {
        userMap.clear();
    }
}

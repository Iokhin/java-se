package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    public Map<String, User> userMap = new HashMap<>(0);

    public User getUserRepositoryItem(String id) {
        return userMap.get(id);
    }

    public void persistUserRepositoryItem(User user) {
        userMap.put(user.getUserId(), user);
    }

    public void mergeUserRepositoryItem(User user) {
        userMap.merge(user.getUserId(), user, (oldVal, newVal) -> new User(newVal.getRoleType(), newVal.getLogin(),
                newVal.getPasswordHash(), oldVal.getUserId()));
    }

    public void removeUserRepositoryItem(String id) {
        userMap.remove(id);
    }

    public void removeAllUserRepositoryItem() {
        userMap.clear();
    }

    public void findAllUserRepositoryItem(String word) {
        int i = 0;
        for (User user : userMap.values()) {
            if (user.getUserId().contains(word)) {
                System.out.println(++i + ". " + user.getLogin() + ", " + user.getUserId());
            }
        }
    }
}

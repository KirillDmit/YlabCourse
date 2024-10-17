package org.example.repository;

import org.example.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, User> users = new HashMap<>();

    public boolean userExists(String email) {
        return users.containsKey(email);
    }

    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    public User getUserByEmail(String email) {
        return users.get(email);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public boolean removeUserByEmail(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
            return true;
        }
        return false;
    }

    public User findUserByEmail(String email) {
        return users.getOrDefault(email, null);
    }
}


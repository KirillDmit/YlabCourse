package org.example.repository;

import org.example.entity.User;

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
}


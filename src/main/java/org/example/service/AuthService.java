package org.example.service;

import org.example.entity.User;
import org.example.repository.UserStorage;

public class AuthService {
    private UserStorage userStorage;

    public AuthService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean registerUser(String email, String password, String name) {
        if (userStorage.userExists(email)) {
            System.out.println("Пользователь с таким email уже существует.");
            return false;
        }
        Long id = generateUserId();
        User newUser = new User(id, email, password, name);
        userStorage.addUser(newUser);
        System.out.println("Регистрация прошла успешно.");
        return true;
    }

    public User loginUser(String email, String password) {
        User user = userStorage.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Вход выполнен успешно.");
            return user;
        } else {
            System.out.println("Неверный email или пароль.");
            return null;
        }
    }

    private Long generateUserId() {
        return System.currentTimeMillis();
    }
}


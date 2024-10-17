package org.example.service;
import org.example.entity.Habit;
import org.example.entity.User;
import org.example.repository.UserStorage;

import java.util.List;

public class AdminService {
    private UserStorage userStorage;

    public AdminService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void viewAllUsersAndHabits(boolean showBlockedUsers) {
        List<User> users = userStorage.getAllUsers();
        for (User user : users) {
            if (showBlockedUsers || !user.isBlocked()) {
                System.out.println("Пользователь: " + user.getName() + " (Email: " + user.getEmail() + ", Статус: " + (user.isBlocked() ? "Заблокирован" : "Активен") + ")");
                for (Habit habit : user.getHabits()) {
                    System.out.println("  Привычка: " + habit.getTitle() + " (Частота: " + habit.getFrequency() + ")");
                }
            }
        }
    }

    public void blockUser(String email) {
        User user = userStorage.findUserByEmail(email);
        if (user != null) {
            if (!user.isBlocked()) {
                user.setBlocked(true);
                System.out.println("Пользователь " + email + " заблокирован.");
            } else {
                System.out.println("Пользователь " + email + " уже заблокирован.");
            }
        } else {
            System.out.println("Пользователь с email " + email + " не найден.");
        }
    }

    public void unblockUser(String email) {
        User user = userStorage.findUserByEmail(email);
        if (user != null) {
            if (user.isBlocked()) {
                user.setBlocked(false);
                System.out.println("Пользователь " + email + " разблокирован.");
            } else {
                System.out.println("Пользователь " + email + " не был заблокирован.");
            }
        } else {
            System.out.println("Пользователь с email " + email + " не найден.");
        }
    }

    public void deleteUser(String email) {
        boolean removed = userStorage.removeUserByEmail(email);
        if (removed) {
            System.out.println("Пользователь " + email + " удален.");
        } else {
            System.out.println("Пользователь с email " + email + " не найден.");
        }
    }

    public void viewUserDetails(String email) {
        User user = userStorage.findUserByEmail(email);
        if (user != null) {
            System.out.println("Детали пользователя: ");
            System.out.println("Имя: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Статус: " + (user.isBlocked() ? "Заблокирован" : "Активен"));
            System.out.println("Привычки:");
            for (Habit habit : user.getHabits()) {
                System.out.println("  Привычка: " + habit.getTitle() + " (Частота: " + habit.getFrequency() + ")");
            }
        } else {
            System.out.println("Пользователь с email " + email + " не найден.");
        }
    }
}


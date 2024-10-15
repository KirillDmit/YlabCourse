package org.example;
import org.example.entity.User;
import org.example.repository.UserStorage;
import org.example.service.AuthService;

import java.util.Scanner;

public class HabitTrackerApp {
    private static UserStorage userStorage = new UserStorage();
    private static AuthService authService = new AuthService(userStorage);
    private static User currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (currentUser == null) {
                System.out.println("1. Регистрация");
                System.out.println("2. Вход");
                System.out.println("3. Выход");
                System.out.print("Выберите действие: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        register(scanner);
                        break;
                    case "2":
                        login(scanner);
                        break;
                    case "3":
                        System.out.println("До свидания!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            } else {
                System.out.println("1. Выйти из аккаунта");
                System.out.print("Выберите действие: ");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    currentUser = null;
                    System.out.println("Вы вышли из аккаунта.");
                } else {
                    System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        authService.registerUser(email, password, name);
    }

    private static void login(Scanner scanner) {
        System.out.print("Введите email: ");
        String email = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        User user = authService.loginUser(email, password);
        if (user != null) {
            currentUser = user;
        }
    }
}


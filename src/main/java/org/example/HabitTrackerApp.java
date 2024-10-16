package org.example;

import org.example.entity.User;
import org.example.repository.UserStorage;
import org.example.service.AuthService;
import org.example.service.HabitService;

import java.time.LocalDate;
import java.util.Scanner;

public class HabitTrackerApp {
    private static UserStorage userStorage = new UserStorage();
    private static AuthService authService = new AuthService(userStorage);
    private static User currentUser = null;
    private static HabitService habitService = null;

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
                System.out.println("1. Добавить привычку");
                System.out.println("2. Редактировать привычку");
                System.out.println("3. Удалить привычку");
                System.out.println("4. Просмотреть привычки");
                System.out.println("5. Отметить выполнение привычки");
                System.out.println("6. Сгенерировать отчет по привычке");
                System.out.println("7. Выйти из аккаунта");
                System.out.print("Выберите действие: ");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        createHabit(scanner);
                        break;
                    case "2":
                        editHabit(scanner);
                        break;
                    case "3":
                        deleteHabit(scanner);
                        break;
                    case "4":
                        habitService.viewHabits();
                        break;
                    case "5":
                        markHabitCompleted(scanner);
                        break;
                    case "6":
                        generateHabitReport(scanner);
                        break;
                    case "7":
                        currentUser = null;
                        habitService = null;
                        System.out.println("Вы вышли из аккаунта.");
                        break;
                    default:
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
            habitService = new HabitService(currentUser);
        }
    }

    private static void createHabit(Scanner scanner) {
        System.out.print("Введите название привычки: ");
        String title = scanner.nextLine();
        System.out.print("Введите описание (опционально): ");
        String description = scanner.nextLine();
        System.out.print("Введите частоту (ежедневно, еженедельно): ");
        String frequency = scanner.nextLine();
        habitService.createHabit(title, description, frequency);
    }

    private static void editHabit(Scanner scanner) {
        System.out.print("Введите ID привычки, которую хотите изменить: ");
        Long habitId = Long.valueOf(scanner.nextLine());
        System.out.print("Введите новое название (оставьте пустым, если не нужно менять): ");
        String newTitle = scanner.nextLine();
        System.out.print("Введите новое описание (оставьте пустым, если не нужно менять): ");
        String newDescription = scanner.nextLine();
        System.out.print("Введите новую частоту (оставьте пустым, если не нужно менять): ");
        String newFrequency = scanner.nextLine();
        habitService.editHabit(habitId, newTitle, newDescription, newFrequency);
    }

    private static void deleteHabit(Scanner scanner) {
        System.out.print("Введите ID привычки, которую хотите удалить: ");
        Long habitId = Long.valueOf(scanner.nextLine());
        habitService.deleteHabit(habitId);
    }

    private static void markHabitCompleted(Scanner scanner) {
        System.out.print("Введите ID привычки, которую хотите отметить выполненной: ");
        Long habitId = Long.valueOf(scanner.nextLine());
        LocalDate today = LocalDate.now();
        habitService.markHabitAsCompleted(habitId, today);
    }

    private static void generateHabitReport(Scanner scanner) {
        System.out.print("Введите ID привычки для отчета: ");
        Long habitId = Long.valueOf(scanner.nextLine());
        System.out.print("Введите начальную дату (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Введите конечную дату (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        habitService.generateHabitReport(habitId, startDate, endDate);
    }
}


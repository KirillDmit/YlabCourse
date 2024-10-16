package org.example.service;

import org.example.entity.Habit;
import org.example.entity.User;
import java.time.LocalDate;

public class HabitService {
    private User currentUser;

    public HabitService(User currentUser) {
        this.currentUser = currentUser;
    }

    public void createHabit(String title, String description, String frequency) {
        Long habitId = generateHabitId();
        Habit newHabit = new Habit(habitId, title, description, frequency);
        currentUser.addHabit(newHabit);
        System.out.println("Привычка добавлена успешно.");
    }

    public void editHabit(Long habitId, String newTitle, String newDescription, String newFrequency) {
        Habit habit = currentUser.findHabitById(habitId);
        if (habit != null) {
            if (!newTitle.isEmpty()) habit.setTitle(newTitle);
            if (!newDescription.isEmpty()) habit.setDescription(newDescription);
            if (!newFrequency.isEmpty()) habit.setFrequency(newFrequency);
            System.out.println("Привычка успешно изменена.");
        } else {
            System.out.println("Привычка не найдена.");
        }
    }

    public void deleteHabit(Long habitId) {
        currentUser.removeHabit(habitId);
        System.out.println("Привычка удалена.");
    }

    public void viewHabits() {
        if (currentUser.getHabits().isEmpty()) {
            System.out.println("У вас нет привычек.");
        } else {
            System.out.println("Ваши привычки:");
            for (Habit habit : currentUser.getHabits()) {
                System.out.println("ID: " + habit.getId() + ", Название: " + habit.getTitle() + ", Частота: " + habit.getFrequency());
            }
        }
    }

    public void markHabitAsCompleted(Long habitId, LocalDate date) {
        Habit habit = currentUser.findHabitById(habitId);
        if (habit != null) {
            habit.markAsCompleted(date);
            System.out.println("Привычка отмечена как выполненная на " + date);
        } else {
            System.out.println("Привычка не найдена.");
        }
    }

    private Long generateHabitId() {
        return System.currentTimeMillis();
    }
}


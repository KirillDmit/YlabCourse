package org.example.entity;

import java.util.List;
import java.util.ArrayList;

public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private List<Habit> habits;

    public User(Long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.habits = new ArrayList<>();
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void removeHabit(String habitId) {
        habits.removeIf(habit -> habit.getId().equals(habitId));
    }

    public Habit findHabitById(String habitId) {
        for (Habit habit : habits) {
            if (habit.getId().equals(habitId)) {
                return habit;
            }
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}


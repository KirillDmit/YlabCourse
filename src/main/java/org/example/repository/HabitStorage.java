package org.example.repository;

import org.example.entity.Habit;

import java.util.ArrayList;
import java.util.List;

public class HabitStorage {

    private List<Habit> habits = new ArrayList<>();

    public List<Habit> getHabits() {
        return habits;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public void removeHabit(Long habitId) {
        habits.removeIf(habit -> habit.getId().equals(habitId));
    }

    public Habit findHabitById(Long habitId) {
        for (Habit habit : habits) {
            if (habit.getId().equals(habitId)) {
                return habit;
            }
        }
        return null;
    }
}

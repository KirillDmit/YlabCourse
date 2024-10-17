package org.example;

import org.example.entity.Habit;
import org.example.entity.User;
import org.example.service.HabitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HabitServiceTest {

    private User user;
    private HabitService habitService;

    @BeforeEach
    public void setUp() {
        user = Mockito.mock(User.class);
        habitService = new HabitService(user);
    }

    @Test
    public void createHabit_shouldAddHabitToUser() {
        // Act
        habitService.createHabit("New Habit", "Description", "daily");

        // Assert
        verify(user, times(1)).addHabit(any(Habit.class));
    }

    @Test
    public void deleteHabit_shouldRemoveHabitFromUser() {
        // Arrange
        Long habitId = 1L;
        when(user.findHabitById(habitId)).thenReturn(new Habit(habitId, "Old Habit", "daily", "everyday"));

        // Act
        habitService.deleteHabit(habitId);

        // Assert
        verify(user, times(1)).removeHabit(habitId);
    }

    @Test
    public void editHabit_shouldModifyExistingHabit() {
        // Arrange
        Long habitId = 1L;
        Habit habit = new Habit(habitId, "Old Habit", "daily", "everyday");
        when(user.findHabitById(habitId)).thenReturn(habit);

        // Act
        habitService.editHabit(habitId, "Updated Habit", "Updated Description", "weekly");

        // Assert
        assertThat(habit.getTitle()).isEqualTo("Updated Habit");
        assertThat(habit.getDescription()).isEqualTo("Updated Description");
        assertThat(habit.getFrequency()).isEqualTo("weekly");
    }
}

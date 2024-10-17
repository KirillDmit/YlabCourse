package org.example.entity;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Habit {
    private Long id;
    private String title;
    private String description;
    private String frequency;
    private LocalDate createdAt;
    private Set<LocalDate> completionDates;

    public Habit(Long id, String title, String description, String frequency) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.createdAt = LocalDate.now();
        this.completionDates = new HashSet<>();
    }

    public void markAsCompleted(LocalDate date) {
        completionDates.add(date);
    }

    public boolean isCompletedOn(LocalDate date) {
        return completionDates.contains(date);
    }

    public Set<LocalDate> getCompletionDates() {
        return completionDates;
    }

    public int getCurrentStreak() {
        int streak = 0;
        LocalDate today = LocalDate.now();

        while (completionDates.contains(today.minusDays(streak))) {
            streak++;
        }
        return streak;
    }

    public double getCompletionPercentage(LocalDate startDate, LocalDate endDate) {
        long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        long completedDays = completionDates.stream()
                .filter(date -> !date.isBefore(startDate) && !date.isAfter(endDate))
                .count();
        return (double) completedDays / totalDays * 100;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFrequency() {
        return frequency;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}


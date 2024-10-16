package org.example.entity;

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


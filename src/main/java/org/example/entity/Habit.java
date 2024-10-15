package org.example.entity;
import java.time.LocalDate;

public class Habit {
    private String id;
    private String title;
    private String description;
    private String frequency;
    private LocalDate createdAt;

    public Habit(String id, String title, String description, String frequency) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.frequency = frequency;
        this.createdAt = LocalDate.now();
    }

    public String getId() {
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

    public void setId(String id) {
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


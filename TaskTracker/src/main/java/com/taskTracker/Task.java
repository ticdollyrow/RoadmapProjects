package com.taskTracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private Long id; //A unique identifier for the task
    private String description; //description
    private TaskStatus status; //The status of the task (todo, in-progress, done)
    private LocalDateTime createdAt; //The date and time when the task was created
    private LocalDateTime updatedAt; //The date and time when the task was last updated

    public Task(Long id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public static String toJson(Task task) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        return "{"
                + "\"id\": " + task.getId() + ","
                + "\"description\": \"" + escapeJson(task.getDescription()) + "\","
                + "\"status\": \"" + task.getStatus().name() + "\","
                + "\"createdAt\": \"" + task.getCreatedAt().format(formatter) + "\","
                + "\"updatedAt\": \"" + task.getUpdatedAt().format(formatter) + "\""
                + "}";
    }

    private static String escapeJson(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}

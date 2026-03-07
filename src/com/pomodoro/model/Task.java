package com.pomodoro.model;

import java.util.Date;
import java.util.UUID;

public abstract class Task implements Exportable {

    private String id;
    private String title;
    private String description;
    private Date dueDate;
    private TaskStatus status;
    private Priority priority;
    private Date reminderTime;
    private Category category;
    private int totalFocusMinutes;
    // เก็บเวลาที่เหลือของ task ไว้ resume ต่อได้ (-1 = ยังไม่เคย start)
    private int remainingSeconds = -1;

    public Task(String title, Priority priority, Category category) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.priority = priority;
        this.category = category;
        this.status = TaskStatus.TODO;
        this.totalFocusMinutes = 0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public boolean isCompleted() {
        return status == TaskStatus.DONE;
    }

    public void setCompleted(boolean completed) {
        if (completed) {
            this.status = TaskStatus.DONE;
        } else {
            this.status = TaskStatus.TODO;
        }
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Date reminderTime) {
        this.reminderTime = reminderTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getTotalFocusMinutes() {
        return totalFocusMinutes;
    }

    public void addFocusMinutes(int minutes) {
        this.totalFocusMinutes += minutes;
    }

    // Getter/Setter สำหรับเวลาที่เหลือ
    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    // Abstract methods for subclasses
    public abstract String getSummary();

    public abstract String getTaskType();
}
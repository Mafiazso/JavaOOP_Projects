package com.pomodoro.model;

public class TaskStatus {

    public static final TaskStatus TODO = new TaskStatus("TODO", "To Do");
    public static final TaskStatus IN_PROGRESS = new TaskStatus("IN_PROGRESS", "In Progress");
    public static final TaskStatus DONE = new TaskStatus("DONE", "Done");
    public static final TaskStatus TRUSH = new TaskStatus("TRUSH", "Trush"); // เพิ่ม Trash

    private final String name;
    private final String label;

    private TaskStatus(String name, String label) {
        this.name = name;
        this.label = label;
    }

    public String name() {
        return name;
    }

    public static TaskStatus[] values() {
        return new TaskStatus[] { TODO, IN_PROGRESS, DONE, TRUSH };
    }

    public static TaskStatus valueOf(String name) {
        if ("TODO".equals(name)) {
            return TODO;
        }
        if ("IN_PROGRESS".equals(name)) {
            return IN_PROGRESS;
        }
        if ("DONE".equals(name)) {
            return DONE;
        }
        if ("TRUSH".equals(name)) {
        	return TRUSH;
        }
        throw new IllegalArgumentException("Unknown status: " + name);
    }

    @Override
    public String toString() {
        return label;
    }
}

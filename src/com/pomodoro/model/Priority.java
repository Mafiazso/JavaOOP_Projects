package com.pomodoro.model;

public class Priority {

    public static final Priority HIGH = new Priority("HIGH", 0);
    public static final Priority MEDIUM = new Priority("MEDIUM", 1);
    public static final Priority LOW = new Priority("LOW", 2);

    private final String name;
    private final int ordinal;

    private Priority(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public String name() {
        return name;
    }

    public int ordinal() {
        return ordinal;
    }

    public static Priority[] values() {
        return new Priority[] { HIGH, MEDIUM, LOW };
    }

    public static Priority valueOf(String name) {
        if ("HIGH".equals(name)) {
            return HIGH;
        }
        if ("MEDIUM".equals(name)) {
            return MEDIUM;
        }
        if ("LOW".equals(name)) {
            return LOW;
        }
        throw new IllegalArgumentException("Unknown priority: " + name);
    }

    @Override
    public String toString() {
        return name;
    }
}

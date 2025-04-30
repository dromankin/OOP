package ru.nsu.romankin.dsl;

public enum Grades {
    TWO("Two"),
    THREE("Three"),
    FOUR("Four"),
    FIVE("Five");

    private final String name;

    Grades (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package ru.nsu.romankin.dsl;

/**
 * Enum for grades.
 */
public enum Grades {

    TWO("Two"),
    THREE("Three"),
    FOUR("Four"),
    FIVE("Five");

    private final String name;

    /**
     * Constructor.
     */
    Grades (String name) {
        this.name = name;
    }

    /**
     * Returns name of grade.
     */
    public String getName() {
        return name;
    }
}

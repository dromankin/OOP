package ru.nsu.romankin.dsl;

/**
 * Class for deadline pass results.
 */
public class PassResults {
    private boolean hard;
    private boolean soft;

    /**
     * Constructor.
     */
    public PassResults(boolean soft, boolean hard) {
        this.hard = hard;
        this.soft = soft;

    }

    /**
     * Hard getter.
     */
    public boolean getHard() {
        return hard;
    }

    /**
     * Soft getter.
     */
    public boolean getSoft() {
        return soft;
    }
}

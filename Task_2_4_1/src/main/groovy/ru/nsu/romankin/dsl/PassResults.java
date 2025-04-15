package ru.nsu.romankin.dsl;

public class PassResults {
    private boolean hard;
    private boolean soft;

    public PassResults(boolean soft, boolean hard) {
        this.hard = hard;
        this.soft = soft;

    }

    public boolean getHard() {
        return hard;
    }

    public boolean getSoft() {
        return soft;
    }
}

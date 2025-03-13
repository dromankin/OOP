package ru.nsu.romankin.pizza;


/**
 * Enumeration for order states.
 */
public enum States {
    QUEUED("queued"),
    COOKING("cooking"),
    STORAGED("storaged"),
    DELIVERING("delivering"),
    DELIVERED("delivered");

    States(String state) {
        this.state = state;
    }
    private final String state;

    public String getState() {
        return state;
    }
}

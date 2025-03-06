package ru.nsu.romankin.pizza;

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

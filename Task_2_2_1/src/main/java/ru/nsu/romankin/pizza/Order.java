package ru.nsu.romankin.pizza;

public class Order {

    private States state = States.QUEUED;
    private final int id;

    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setState(States state) {
        this.state = state;
    }

    public String getState() {
        return state.getState();
    }
}


package ru.nsu.romankin.pizza;

/**
 * Class representing order.
 */

public class Order {

    private States state = States.QUEUED;
    private final int id;

    public Order(int id) {
        this.id = id;
    }



    public void setState(States state) {
        this.state = state;
    }

    public String getState() {
        return state.getState();
    }

    public String toString() {
        return "Order: " + id + "; state: " + this.getState();
    }
}


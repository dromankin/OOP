package ru.nsu.romankin.pizza;

import static java.lang.Thread.sleep;

public class Courier implements Runnable{

    private int id;
    private int capacity;
    private int deliverSpeed;
    private Storage storage;

    public Courier(int id, int capacity, Storage storage, int deliverSpeed) {
        this.capacity = capacity;
        this.id = id;
        this.storage = storage;
        this.deliverSpeed = deliverSpeed;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public void run() {
        try {
            Order order = storage.takeOrder();
            order.setState(States.DELIVERING);
            System.out.printf("Order: %d; state:%s\n", order.getId(), order.getState());
            sleep(deliverSpeed);
            order.setState(States.DELIVERED);
            System.out.printf("Order: %d; state:%s\n", order.getId(), order.getState());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

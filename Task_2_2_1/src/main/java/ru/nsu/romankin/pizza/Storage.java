package ru.nsu.romankin.pizza;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Thread-safe structure for order storage.
 */
public class Storage {
    private int capacity;
    private Queue<Order> storage = new LinkedList<>();

    public Storage(int capacity) {
        this.capacity = capacity;
    }


    /**
     * Synchronized adding order method.
     */
    public synchronized void addOrder(Order order) throws InterruptedException {
        while (storage.size() >= capacity) {
            wait();
        }
        storage.add(order);
        notifyAll();
    }

    /**
     * Synchronized taking order method.
     */
    public synchronized Order takeOrder() throws InterruptedException {
        while (storage.isEmpty()) {
            wait();
        }
        Order order = storage.poll();
        notifyAll();
        return order;
    }



}

package ru.nsu.romankin.pizza;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Storage {
    private int capacity;
    private Queue<Order> storage = new LinkedList<>();

    public Storage(int capacity) {
        this.capacity = capacity;
    }



    public synchronized void addOrder(Order order) throws InterruptedException {
        while(storage.size() >= capacity) {
            wait();
        }
        storage.add(order);
        notifyAll();
    }

    public synchronized Order takeOrder() throws InterruptedException {
        while (storage.isEmpty()) {
            wait();
        }
        Order order = storage.poll();
        notifyAll();
        return order;
    }



}

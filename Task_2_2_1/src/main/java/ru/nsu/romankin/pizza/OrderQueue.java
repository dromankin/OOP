package ru.nsu.romankin.pizza;


import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private Queue<Order> queue = new LinkedList<>();


    public synchronized void addOrder(Order order) {
        queue.add(order);
        notifyAll();
    }

    public synchronized Order takeOrder() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        Order order = queue.remove();
        notifyAll();
        return order;
    }

}

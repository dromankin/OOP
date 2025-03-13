package ru.nsu.romankin.pizza;


import java.util.LinkedList;
import java.util.Queue;


/**
 * Thread-safe queue structure for orders.
 */
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
        Order order = queue.poll();
        notifyAll();
        return order;
    }

}

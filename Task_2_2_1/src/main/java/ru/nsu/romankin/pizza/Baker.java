package ru.nsu.romankin.pizza;


import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

public class Baker extends Thread {
    private int id;
    private int speed;
    private OrderQueue orderQueue;
    private Storage storage;
    private AtomicBoolean working = new AtomicBoolean();
    public Baker(int id, int speed, OrderQueue orderQueue, Storage storage) {
        this.id = id;
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.storage = storage;

    }



    @Override
    public void run() {
        while(!isInterrupted()) {
            try {

                Order order = orderQueue.takeOrder();
                if (order == null) {
                    break;
                }
                order.setState(States.COOKING);
                System.out.println(order + " baker id: " + id);
                sleep(speed);
                order.setState(States.STORAGED);
                System.out.println(order + " baker id: " + id);
                storage.addOrder(order);

            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

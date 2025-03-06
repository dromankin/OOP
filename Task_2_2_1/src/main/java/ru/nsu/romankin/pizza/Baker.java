package ru.nsu.romankin.pizza;

import javax.swing.plaf.nimbus.State;

import static java.lang.Thread.sleep;

public class Baker implements Runnable{
    private int id;
    private int speed;
    private OrderQueue orderQueue;
    private Storage storage;
    public Baker(int id, int speed, OrderQueue orderQueue, Storage storage) {
        this.id = id;
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.storage = storage;
    }

    public int getId() {
        return id;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Order order = orderQueue.takeOrder();
                order.setState(States.COOKING);
                System.out.printf("Order: %d; state:%s\n", order.getId(), order.getState());
                sleep(speed);
                order.setState(States.STORAGED);
                storage.addOrder(order);
                System.out.printf("Order: %d; state:%s\n", order.getId(), order.getState());
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

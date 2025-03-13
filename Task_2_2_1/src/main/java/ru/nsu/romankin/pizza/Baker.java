package ru.nsu.romankin.pizza;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Baker class(Thread).
 */
public class Baker extends Thread {
    private int id;
    private int speed;
    private OrderQueue orderQueue;
    private Storage storage;
    private AtomicBoolean working = new AtomicBoolean();

    /**
     * Class constructor.
     * @param id - baker's id
     * @param speed - baker's speed
     * @param orderQueue - order queue used by baker
     * @param storage - storage used by baker
     */
    public Baker(int id, int speed, OrderQueue orderQueue, Storage storage) {
        this.id = id;
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.storage = storage;

    }



    @Override
    public void run() {
        while (!isInterrupted()) {
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

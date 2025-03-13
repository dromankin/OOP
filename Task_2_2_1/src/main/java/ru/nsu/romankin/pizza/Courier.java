package ru.nsu.romankin.pizza;

/**
 * Courier class(thread).
 */

public class Courier extends Thread {

    private int id;
    private int capacity;
    private int deliverSpeed;
    private Storage storage;
    private Pizzeria pizzeria;

    /**
     * Class constructor.
     * @param id - courier's id
     * @param capacity - courier's capacity
     * @param storage - storage used by courier
     * @param deliverSpeed - deliver speed
     * @param pizzeria - pizzeria used by courier
     */
    public Courier(int id, int capacity, Storage storage, int deliverSpeed, Pizzeria pizzeria) {
        this.capacity = capacity;
        this.id = id;
        this.storage = storage;
        this.deliverSpeed = deliverSpeed;
        this.pizzeria = pizzeria;

    }


    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Order order = storage.takeOrder();
                if (order == null) {
                    break;
                }
                order.setState(States.DELIVERING);
                System.out.println(order + " courier id: " + id);
                sleep(deliverSpeed);
                order.setState(States.DELIVERED);
                System.out.println(order + " courier id: " + id);
                pizzeria.courierTookOrder();
            } catch (InterruptedException e) {
                return;
            }
        }

    }
}

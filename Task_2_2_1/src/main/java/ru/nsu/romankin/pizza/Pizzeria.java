package ru.nsu.romankin.pizza;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class representing pizzeria.
 * There are start, stop and adding order methods
 */

public class Pizzeria {
    private List<Baker> bakers;
    private List<Courier> couriers;
    private OrderQueue orderQueue = new OrderQueue();
    private Storage storage;
    private int ordersCount = 0;
    private int workTime;
    private JsonConfig config;
    private AtomicBoolean working = new AtomicBoolean();

    /**
     * Class constructor.
     *
     * @param jsonFilename - path to json config file.
     */
    public Pizzeria(String jsonFilename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        config = objectMapper.readValue(new File(jsonFilename), JsonConfig.class);
        this.storage = new Storage(config.getStorageCapacity());
        this.bakers = new ArrayList<>(config.getBakersCount());
        this.couriers = new ArrayList<>(config.getCouriersCount());
        this.workTime = config.getWorkTime();
        for (int i = 0; i < config.getBakersCount(); i++) {
            bakers.add(i, new Baker(i, config.getBakersSpeeds()[i], orderQueue, storage));
        }
        for (int i = 0; i < config.getCouriersCount(); i++) {
            couriers.add(i, new Courier(i, config.getCouriersCapacities()[i],
                storage, config.getCouriersSpeeds()[i], this));
        }

    }

    /**
     * Order count decrement method.
     */
    public synchronized void courierTookOrder() {
        ordersCount--;

    }

    /**
     * Starting pizzeria method.
     */
    public void start() {
        working.set(true);

        for (Baker baker : bakers) {
            baker.start();
        }
        for (Courier courier : couriers) {
            courier.start();
        }
    }

    /**
     * Returns pizzeria work time.
     */
    public int getWorkTime() {
        return workTime;
    }

    /**
     * Synchronized method for orders count.
     */
    public synchronized int getOrdersCount() {
        return ordersCount;
    }

    /**
     * Method stops pizzeria and interrupts threads.
     */
    public void stop() throws InterruptedException {
        working.set(false);
        while (getOrdersCount() > 0) {
            Thread.sleep(1000);
            System.out.println("Pizzeria has closed, orders remaining: " + ordersCount);
        }
        for (Baker baker : bakers) {
            baker.interrupt();

        }
        for (Courier courier : couriers) {
            courier.interrupt();
        }



    }

    /**
     * Adding order method.
     *
     * @param order - order
     */
    public void addOrder(Order order) {
        if (working.get()) {

            synchronized (orderQueue) {
                orderQueue.addOrder(order);
                ordersCount++;
                orderQueue.notifyAll();
            }
        }
    }
}

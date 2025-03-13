package ru.nsu.romankin.pizza;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Pizzeria {
    private List<Baker> bakers;
    private List<Courier> couriers;
    private OrderQueue orderQueue = new OrderQueue();
    private Storage storage;
    private int ordersCount = 0;
    private int workTime;
    private JsonConfig config;
    private AtomicBoolean working = new AtomicBoolean();
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
            couriers.add(i, new Courier(i, config.getCouriersCapacities()[i], storage, config.getCouriersSpeeds()[i], this));
        }

    }

    public synchronized void courierTookOrder() {
            ordersCount--;

    }

    public void start() {
        working.set(true);

        for (Baker baker : bakers) {
            baker.start();
        }
        for (Courier courier : couriers) {
            courier.start();
        }
    }

    public int getWorkTime() {
        return workTime;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void stop() throws InterruptedException {
        working.set(false);
        while (ordersCount > 0) {
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

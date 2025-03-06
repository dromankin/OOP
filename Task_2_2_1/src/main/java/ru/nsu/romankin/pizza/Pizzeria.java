package ru.nsu.romankin.pizza;

import java.util.ArrayList;
import java.util.List;

public class Pizzeria {
    private List<Thread> bakers;
    private List<Thread> couriers;
    private OrderQueue orderQueue;
    private Storage storage;
    public Pizzeria(int bakersCount, int couriersCount, int[] couriersCapacities,
                    int[] couriersSpeeds, int[] bakerSpeeds, int storageCapacity) {
        this.storage = new Storage(storageCapacity);
        this.bakers = new ArrayList<>(bakersCount);
        this.couriers = new ArrayList<>(couriersCount);
        for (int i = 0; i < bakersCount; i++) {
            bakers.add(i, new Thread(new Baker(i, bakerSpeeds[i], orderQueue, storage)));
        }
        for (int i = 0; i < couriersCount; i++) {
            couriers.add(i, new Thread(new Courier(i, couriersCapacities[i], storage, couriersSpeeds[i])));
        }

    }

    public void start() {
        for (Thread baker : bakers) {
            baker.start();
        }
        for (Thread courier : couriers) {
            courier.start();
        }
    }


    public void stop() {
        for (Thread baker : bakers) {
            baker.interrupt();
        }
        for (Thread courier : couriers) {
            courier.interrupt();
        }
    }
}

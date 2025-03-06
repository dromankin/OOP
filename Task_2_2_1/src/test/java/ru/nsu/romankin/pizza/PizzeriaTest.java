package ru.nsu.romankin.pizza;

import org.junit.jupiter.api.Test;

class PizzeriaTest {
    @Test
    void initTest() throws InterruptedException {
        int bakersCount = 3;
        int couriersCount = 2;
        int[] couriersCapacities = {1, 2};
        int[] couriersSpeeds = {500, 1000};
        int[] bakersSpeeds = {250, 400, 600};
        int storageCapacity = 5;
        int ordersCount = 10;
        Pizzeria pizzeria = new Pizzeria(bakersCount, couriersCount, couriersCapacities,
                couriersSpeeds, bakersSpeeds, storageCapacity);
        Order[] orders = new Order[ordersCount];
        for (int i = 0; i < ordersCount; i++) {
            orders[i] = new Order(i);
            pizzeria.addOrder(orders[i]);
        }
        pizzeria.start();
        Thread.sleep(3000);
        pizzeria.stop();
    }
}
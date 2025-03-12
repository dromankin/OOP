package ru.nsu.romankin.pizza;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PizzeriaTest {
    @Test
    void baseTest() throws InterruptedException, IOException {

        int ordersCount = 10;
        Pizzeria pizzeria = new Pizzeria("src/test/resources/config.json");
        Order[] orders = new Order[ordersCount];

        pizzeria.start();
        for (int i = 0; i < ordersCount; i++) {
            orders[i] = new Order(i);
            pizzeria.addOrder(orders[i]);
        }
        Thread.sleep(pizzeria.getWorkTime());
        pizzeria.stop();
        assertEquals(pizzeria.getOrdersCount(), 0);
    }

    @Test
    void moreOrdersTest() throws IOException, InterruptedException {
        int ordersCount = 100;
        Pizzeria pizzeria = new Pizzeria("src/test/resources/config2.json");
        Order[] orders = new Order[ordersCount];

        pizzeria.start();
        for (int i = 0; i < ordersCount; i++) {
            orders[i] = new Order(i);
            pizzeria.addOrder(orders[i]);
        }
        Thread.sleep(pizzeria.getWorkTime());
        pizzeria.stop();
        assertEquals(pizzeria.getOrdersCount(), 0);
    }

    @Test
    void noOrdersTest() throws IOException, InterruptedException {
        int ordersCount = 0;
        Pizzeria pizzeria = new Pizzeria("src/test/resources/config.json");
        Order[] orders = new Order[ordersCount];

        pizzeria.start();
        for (int i = 0; i < ordersCount; i++) {
            orders[i] = new Order(i);
            pizzeria.addOrder(orders[i]);
        }
        Thread.sleep(pizzeria.getWorkTime());
        pizzeria.stop();
        assertEquals(pizzeria.getOrdersCount(), 0);
    }

    @Test
    void smallTimeTest() throws IOException, InterruptedException {
        int ordersCount = 10;
        Pizzeria pizzeria = new Pizzeria("src/test/resources/smalltime.json");
        Order[] orders = new Order[ordersCount];

        pizzeria.start();
        for (int i = 0; i < ordersCount; i++) {
            orders[i] = new Order(i);
            pizzeria.addOrder(orders[i]);
        }
        Thread.sleep(pizzeria.getWorkTime());
        pizzeria.stop();
        assertEquals(pizzeria.getOrdersCount(), 0);
    }

    @Test
    void capacityTest() throws IOException, InterruptedException {
        int ordersCount = 15;
        Pizzeria pizzeria = new Pizzeria("src/test/resources/capacity.json");
        Order[] orders = new Order[ordersCount];

        pizzeria.start();
        for (int i = 0; i < ordersCount; i++) {
            orders[i] = new Order(i);
            pizzeria.addOrder(orders[i]);
        }
        Thread.sleep(pizzeria.getWorkTime());
        pizzeria.stop();
        assertEquals(pizzeria.getOrdersCount(), 0);
    }
}
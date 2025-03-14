package ru.nsu.romankin.pizza;

/**
 * Class for JSON pizzeria configuration used in pizzeria constructor.
 */

public class JsonConfig {
    private int bakersCount;
    private int couriersCount;
    private int[] couriersCapacities;
    private int[] couriersSpeeds;
    private int[] bakersSpeeds;
    private int storageCapacity;
    private int workTime;

    public int getBakersCount() {
        return bakersCount;
    }

    public int getCouriersCount() {
        return couriersCount;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int[] getBakersSpeeds() {
        return bakersSpeeds;
    }

    public int getWorkTime() {
        return workTime;
    }

    public int[] getCouriersCapacities() {
        return couriersCapacities;
    }

    public int[] getCouriersSpeeds() {
        return couriersSpeeds;
    }
}

package ru.nsu.romankin.prime;

public interface PrimeInterface {
    boolean findNonPrime(int[] array) throws InterruptedException;
    static boolean isNotPrime(int num){
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }
}

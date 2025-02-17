package ru.nsu.romankin.prime;

/**
 * Interface for finding non-prime numbers.
 */

public interface PrimeInterface {
    boolean findNonPrime(int[] array) throws InterruptedException;

    /**
     * Function that checks if number is prime or not.
     *
     * @param num - number to check;
     *
     * @return true - if it is not prime, false - if it is prime;
     */

    static boolean isNotPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        return false;
    }
}

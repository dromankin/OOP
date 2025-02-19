package ru.nsu.romankin.prime;

/**
 * Class for sequential non-prime numbers finding.
 */

public class SequentialPrime implements PrimeInterface {


    @Override
    public boolean findNonPrime(int[] numbers) {

        for (int num : numbers) {
            if (PrimeInterface.isNotPrime(num)) {
                return true;
            }
        }
        return false;
    }
}

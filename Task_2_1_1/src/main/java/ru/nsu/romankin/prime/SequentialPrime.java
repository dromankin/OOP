package ru.nsu.romankin.prime;

public class SequentialPrime implements PrimeInterface {


    @Override
    public boolean findNonPrime(int[] numbers) {

        for (int num: numbers) {
            if (PrimeInterface.isNotPrime(num)) {
                return true;
            }
        }
        return false;
    }
}

package ru.nsu.romankin.prime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for finding non-prime numbers using parallelStream method.
 */

public class ParallelStreamPrime implements PrimeInterface {
    List<Integer> list = new ArrayList<>();

    @Override
    public boolean findNonPrime(int[] array) {
        list = Arrays.stream(array).boxed().collect(Collectors.toList());
        return list.parallelStream().anyMatch(PrimeInterface::isNotPrime);
    }
}

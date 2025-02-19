package ru.nsu.romankin.prime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;


class PrimeInterfaceTest {

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void primeTestTrue(PrimeInterface prime) throws InterruptedException {
        int[] arr = {6, 8, 7, 13, 5, 9, 4};
        assertTrue(prime.findNonPrime(arr));
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void primeTestFalse(PrimeInterface prime) throws InterruptedException {
        int[] arr2 = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
            6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertFalse(prime.findNonPrime(arr2));
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void primeTestTrueBigData(PrimeInterface prime) throws InterruptedException {
        int n = 10000;
        int bound = 1000000;
        int[] arr = new int[n];
        Random rnd = new Random();
        int index = rnd.nextInt(n - 1);
        int number = rnd.nextInt(n);
        int i = 0;
        while (i < n) {
            if (i == index) {
                if (PrimeInterface.isNotPrime(number)) {
                    arr[i] = number;
                    number = rnd.nextInt(bound);
                    i++;
                } else {
                    number = rnd.nextInt(bound);
                }
            } else {
                if (!PrimeInterface.isNotPrime(number)) {
                    arr[i] = number;
                    number = rnd.nextInt(bound);
                    i++;
                } else {
                    number = rnd.nextInt(bound);

                }
            }
        }
        boolean bool = prime.findNonPrime(arr);
        assertTrue(prime.findNonPrime(arr));
    }

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void primeTestFalseBigData(PrimeInterface prime) throws InterruptedException {
        int n = 10000;
        int bound = 1000000;
        int[] arr = new int[n];
        Random rnd = new Random();
        int index = rnd.nextInt(n - 1);
        int number = rnd.nextInt(bound);
        int i = 0;
        while (i < n) {
            if (!PrimeInterface.isNotPrime(number)) {
                arr[i] = number;
                number = rnd.nextInt(bound);
                i++;
            } else {
                number = rnd.nextInt(bound);

            }
        }

        boolean bool = prime.findNonPrime(arr);
        assertFalse(prime.findNonPrime(arr));
    }

    @Test
    void isNotPrimeTest() {
        boolean flag = false;
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
            41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157,
            163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223,
            227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293};
        for (int num : arr) {
            flag = PrimeInterface.isNotPrime(num);
        }
        assertFalse(flag);
    }

    @Test
    void isNotPrimeTestTrue() {
        boolean flag = false;
        int[] arr = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37,
            41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157,
            163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223,
            227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 294};
        for (int num : arr) {
            flag = PrimeInterface.isNotPrime(num);
        }
        assertTrue(flag);
    }

    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new SequentialPrime()),
                    Arguments.of(new ParallelStreamPrime()),
                    Arguments.of(new MultiThreadPrime(1)),
                    Arguments.of(new MultiThreadPrime(2)),
                    Arguments.of(new MultiThreadPrime(3)),
                    Arguments.of(new MultiThreadPrime(4)),
                    Arguments.of(new MultiThreadPrime(8))
            );
        }
    }
}
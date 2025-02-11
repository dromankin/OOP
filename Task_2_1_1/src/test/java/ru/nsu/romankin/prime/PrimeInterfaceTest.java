package ru.nsu.romankin.prime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PrimeInterfaceTest {

    @ParameterizedTest
    @ArgumentsSource(TestArgumentsProvider.class)
    void primeTest(PrimeInterface prime) throws InterruptedException {
        int[] arr = {6, 8, 7, 13, 5, 9, 4};
        int[] arr2 = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        assertTrue(prime.findNonPrime(arr));
        assertFalse(prime.findNonPrime(arr2));
    }



    static class TestArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new SequentialPrime()),
                    Arguments.of(new ParallelStreamPrime()),
                    Arguments.of(new MultiThreadPrime(2))
            );
        }
    }
}
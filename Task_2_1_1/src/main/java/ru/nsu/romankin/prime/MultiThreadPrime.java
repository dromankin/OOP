package ru.nsu.romankin.prime;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class for multithreaded non-prime numbers finding.
 */

public class MultiThreadPrime implements PrimeInterface {
    private int threadsNum;

    MultiThreadPrime(int threadsNum) {
        this.threadsNum = threadsNum;
    }

    @Override
    public boolean findNonPrime(int[] array) throws InterruptedException {
        Thread[] threads = new Thread[threadsNum];
        AtomicBoolean containsNotPrime = new AtomicBoolean(false);
        int arraySize = array.length;
        int shift = arraySize / threadsNum + 1;
        int indexFirst;
        int indexLast;
        for (int i = 0; i < threadsNum; i++) {
            indexFirst = i * shift;
            if (indexFirst + shift - 1 >= arraySize - 1) {
                indexLast = arraySize;
            } else {
                indexLast = indexFirst + shift - 1;
            }
            threads[i] = createThread(indexFirst, indexLast, array, containsNotPrime);
        }

        for (int i = 0; i < threadsNum; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadsNum; i++) {
            threads[i].join();
        }
        return containsNotPrime.get();
    }

    private Thread createThread(int start, int end, int[] array, AtomicBoolean flag) {
        return new Thread(() -> {
            for (int i = start; i <= end && !flag.get(); i++) {
                if (PrimeInterface.isNotPrime(array[i])) {
                    flag.set(true);
                    break;
                }
            }
        });
    }

}

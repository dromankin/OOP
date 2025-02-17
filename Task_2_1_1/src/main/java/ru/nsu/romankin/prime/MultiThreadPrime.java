package ru.nsu.romankin.prime;

/**
 * Class for multithreaded non-prime numbers finding.
 */

public class MultiThreadPrime implements PrimeInterface {
    private int threadsNum;
    private int[] array;
    private Thread [] threads;
    private boolean containsNotPrime = false;

    MultiThreadPrime(int threadsNum) {
        this.threadsNum = threadsNum;
        threads = new Thread[threadsNum];
    }

    @Override
    public boolean findNonPrime(int[] array) throws InterruptedException {
        containsNotPrime = false;
        this.array = array;
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
            threads[i] = createThread(indexFirst, indexLast);
        }

        for (int i = 0; i < threadsNum; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadsNum; i++) {
            threads[i].join();
        }
        return containsNotPrime;
    }

    private Thread createThread(int start, int end) {
        return new Thread(() -> {
            for (int i = start; i <= end && !containsNotPrime; i++) {
                if (PrimeInterface.isNotPrime(array[i])) {
                    containsNotPrime = true;
                    break;
                }
            }
        });
    }

}

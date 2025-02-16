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
        int j = 0;
        for (int i = 0; i < threadsNum; i++) {
            if (i * shift + shift - 1 >= arraySize - 1) {
                threads[i] = new Thread(new PrimeThread(i * shift, arraySize));
            } else {
                threads[i] = new Thread(new PrimeThread(i * shift, i * shift + shift - 1));
            }
        }

        for (int i = 0; i < threadsNum; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threadsNum; i++) {
            threads[i].join();
        }
        return containsNotPrime;
    }

    private class PrimeThread implements Runnable {
        int start;
        int end;

        PrimeThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i <= end && !containsNotPrime; i++) {
                if (PrimeInterface.isNotPrime(array[i])) {
                    containsNotPrime = true;
                    break;
                }
            }
        }
    }
}

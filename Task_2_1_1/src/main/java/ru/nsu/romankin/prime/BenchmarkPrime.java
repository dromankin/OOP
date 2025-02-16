package ru.nsu.romankin.prime;

import java.util.Random;

public class BenchmarkPrime {

    private static long test(PrimeInterface prime) throws InterruptedException {
        int n = 1000000;
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
        long time = System.currentTimeMillis();
        boolean bool = prime.findNonPrime(arr);
        time = System.currentTimeMillis() - time;
        return time;
    }

    public static void main(String[] args) throws InterruptedException {
        SequentialPrime seq = new SequentialPrime();
        PrimeInterface par = new ParallelStreamPrime();
        MultiThreadPrime thread2 = new MultiThreadPrime(2);
        MultiThreadPrime thread4 = new MultiThreadPrime(4);
        MultiThreadPrime thread8 = new MultiThreadPrime(8);
        System.out.println(test(seq));
        System.out.println(test(par));
        System.out.println(test(thread2));
        System.out.println(test(thread4));
        System.out.println(test(thread8));
    }
}

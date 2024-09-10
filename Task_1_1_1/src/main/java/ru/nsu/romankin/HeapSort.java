package ru.nsu.romankin;

import java.util.Random;

/**main class containing heap building and heap sorting function.**/

public class HeapSort {
    /**
    * this function builds a heap from array.
    *
    * @param arr - array,
    *
    * @param i - index of element in tree,
    *
    * @param n - amount of elements.
    */

    public static void build_heap(int[] arr, int i, int n) {

        int indexOfMax = i; /*choosing cuurent index as index of maximal element*/
        if ((2 * i + 1) < n && arr[2 * i + 1] > arr[indexOfMax]) {
            indexOfMax = 2 * i + 1; /*choosing left son if it is bigger**/
        }
        if ((2 * i + 2) < n && arr[2 * i + 2] > arr[indexOfMax]) {
            indexOfMax = 2 * i + 2; /*choosing right son if it is bigger**/
        }
        if (i != indexOfMax) { /*if index of max changed*/
            int temp = arr[indexOfMax]; /* swaping the elements**/
            arr[indexOfMax] = arr[i];
            arr[i] = temp;
            build_heap(arr, indexOfMax, n); /*recursive call of the function*/
        }
    }
    /**
     * this is final sorting function using build_heap function.
     */

    public static void heapsort(int[] arr) {
        int n = arr.length; //length of array
        for (int j = n / 2; j >= 0; j--) { //building a heap using build_heap
            // function for each element with index than n/2
            build_heap(arr, j, n);
        }
        for (int i = n - 1; i >= 0; i--) {

            int t = arr[0];   /*swapping first and last elements;
            after that the biggest element will be in the end of array*/
            arr[0] = arr[i];
            arr[i] = t;
            build_heap(arr, 0, i); /*doing the same until
            there will be 0 elements in current tree*/
        }
    }

    /**
     * main function.
     * */

    public static void main(String[] args) {
        int[] arr = new int[10000];
        Random rd = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt();
        }
        long m = System.currentTimeMillis();
        heapsort(arr);
        System.out.printf("%d", System.currentTimeMillis() - m);
    }
}
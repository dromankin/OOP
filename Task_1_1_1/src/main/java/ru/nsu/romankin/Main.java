package ru.nsu.romankin;

/**main class containing heap building and heap sorting function.**/

public class Main {
    /**
     * arr - array, i - index of element in tree, n - amount of elements.
     * this function builds a heap from array.
     */

    public static int[] build_heap(int[] arr, int i,int n) {

        int indexofmax = i; /*choosing cuurent index as index of maximal element*/
        if ((2 * i + 1) < n && arr[2 * i + 1] > arr[indexofmax]) {
            indexofmax = 2 * i + 1; /*choosing left son if it is bigger**/
        }
        if ((2 * i + 2) < n && arr[2 * i + 2] > arr[indexofmax]) {
            indexofmax = 2 * i + 2; /*choosing right son if it is bigger**/
        }
        if (i != indexofmax) { /*if index of max changed*/
            int temp = arr[indexofmax]; /* swaping the elements**/
            arr[indexofmax] = arr[i];
            arr[i] = temp;
            build_heap(arr, indexofmax, n); /*recursive call of the function*/
        }
        return arr;
    }
    /**
     * this is final sorting function using build_heap function.
     */

    public static int[] heapsort(int[] arr) {
        int n=arr.length; //length of array
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
        return arr;
    }

    /**
     * main function.
     * */

    public static void main(String[] args) {

    }
}
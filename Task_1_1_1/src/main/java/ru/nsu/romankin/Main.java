package ru.nsu.romankin;

public class Main {
    public static int[] build_heap(int[] arr, int i,int n) { /*arr - array, i - index, n - amount of elements*/
        int index_of_max = i;
        if ((2 * i + 1) < n && arr[2 * i + 1] > arr[index_of_max]) {
            index_of_max=2*i+1; /*left son*/
        }
        if ((2 * i + 2) < n && arr[2 * i + 2] > arr[index_of_max] ) {
            index_of_max = 2 * i + 2; /*right son*/
        }
        if (i != index_of_max) {
            int temp = arr[index_of_max];
            arr[index_of_max] = arr[i];
            arr[i] = temp;
            build_heap(arr, index_of_max, n);
        }
        return arr;
    }
    public static int[] heapsort(int[] arr){
        int n=arr.length;
        for (int j = n / 2; j >= 0; j--) {
            build_heap(arr, j, n);
        }
        for (int i = n - 1; i >= 0; i--){

            int t = arr[0];
            arr[0] = arr[i];
            arr[i] =t;
            build_heap(arr, 0, i);
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] arr=new int[] {3,4,1,2,5};
        build_heap(arr,0,5);
        for (int i=0;i<5;i++) {
            System.out.print(arr[i]);
        }
    }
}
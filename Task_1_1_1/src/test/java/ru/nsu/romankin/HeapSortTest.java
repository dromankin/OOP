package ru.nsu.romankin;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {
    @Test
    void test_positive_result() {
        int[] arr = new int[] {3, 1, 2, 5, 4};
        HeapSort.heapsort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, arr);
    }

    @Test
    void test_negative_result() {
        int[] arr = new int[] {3, 1, 2, 5, 4};
        HeapSort.heapsort(arr);
        assertFalse(Arrays.equals(new int[] {1, 3, 2, 4, 5}, arr));
    }

    @Test
    void test_negative_numbers() {
        int[] arr = new int[] {-5, -8, 12, 1, 0};
        HeapSort.heapsort(arr);
        assertArrayEquals(new int[] {-8, -5, 0, 1, 12}, arr);
    }

    @Test
    void test_negative_numbers_negative_result() {
        int[] arr = new int[] {-5, -8, 12, 1, 0};
        HeapSort.heapsort(arr);
        assertFalse(Arrays.equals(new int[] {12, -8, -5, 1, 0}, arr));
    }

    @Test
    void test_repeating_numbers() {
        int[] arr = new int[] {1, 1, 1, 87, 4, 0, 8, 5, 7, 7};
        HeapSort.heapsort(arr);
        assertArrayEquals(new int[]{0, 1, 1, 1, 4, 5, 7, 7, 8, 87}, arr);
    }

    @Test
    void test_one_element() {
        int[] arr = new int[] {-8};
        HeapSort.heapsort(arr);
        assertArrayEquals(new int[] {-8}, arr);
    }

    @Test
    void test_one_element_neg_result() {
        int[] arr = new int[] {0};
        HeapSort.heapsort(arr);
        assertFalse(Arrays.equals(new int[] {-8}, arr));
    }

    @Test
    void test_different_arrays() {
        int[] arr = new int[] {-8, 0, 4, 5, 6, 13};
        HeapSort.heapsort(arr);
        assertFalse(Arrays.equals(new int[] {1, 2, 3, 4, 5, 9, 45}, arr));
    }

    @Test
    void test_only_same_numbers() {
        int[] arr = new int[] {0, 0, 0, 0, 0};
        HeapSort.heapsort(arr);
        assertTrue(Arrays.equals(new int[] {0, 0, 0, 0, 0}, arr));
    }

    @Test
    void test_no_elements() {
        int[] arr = new int[] {};
        HeapSort.heapsort(arr);
        assertTrue(Arrays.equals(new int[] {}, arr));
    }

    @Test
    void test_heap_building() {
        int[] arr = new int[] {3, 4, 1, 2, 5};
        int n = arr.length;
        for (int j = n / 2; j >= 0; j--) {
            HeapSort.build_heap(arr, j, n);
        }
        assertArrayEquals(new int[] {5, 4, 1, 2, 3}, arr);
    }

    @Test
    void test_heap_building_wrong() {
        int[] arr = new int[] {3, 4, 1, 2, 5};
        int n = arr.length;
        for (int j = n / 2; j >= 0; j--) {
            HeapSort.build_heap(arr, j, n);
        }
        assertFalse(Arrays.equals(new int[] {4, 2, 1, 5, 3}, arr));
    }

    @Test
    void test_heap_building_and_sort() {
        int[] arr = new int[] {3, 4, 1, 2, 5};
        HeapSort.build_heap(arr, 0, arr.length);
        assertArrayEquals(new int[] {4, 5, 1, 2, 3}, arr);
    }

    @Test
    void test_main() {
        HeapSort.main(null);
        assertTrue(true);
    }
}
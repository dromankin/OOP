package ru.nsu.romankin;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void test_1_positive_result() {
        assertArrayEquals (new int[] {1, 2, 3, 4, 5},Main.heapsort(new int[] {3, 1, 2, 5, 4}));
    }

    @Test
    void test_2_negative_result () {
        assertFalse(Arrays.equals(new int[] {1, 3, 2, 4, 5}, Main.heapsort(new int[] {3, 1, 2, 5, 4})));
    }

    @Test
    void test_3_negative_numbers() {
        assertArrayEquals(new int[] {-8, -5, 0, 1, 12},Main.heapsort(new int[] {-5, -8, 12, 1, 0}));
    }

    @Test
    void test_4_negative_numbers_negative_result() {
        assertFalse(Arrays.equals(new int[] {12, -8, -5, 1, 0},Main.heapsort(new int[] {-5, -8, 12, 1, 0})));
    }

    @Test
    void test_5_repeating_numbers() {
        assertArrayEquals(new int[] {0, 1, 1, 1, 4, 5, 7, 7, 8, 87},Main.heapsort(new int[] {1, 1, 1, 87, 4, 0, 8, 5, 7, 7}));
    }

    @Test
    void test_6_one_element() {
        assertArrayEquals(new int[] {-8}, Main.heapsort(new int[] {-8}));
    }

    @Test
    void test_7_one_element_neg_result() {
        assertFalse(Arrays.equals(new int[] {-8}, Main.heapsort(new int[] {0})));
    }

    @Test
    void test_8_different_arrays() {
        assertFalse(Arrays.equals(new int[] {1, 2, 3, 4, 5, 9, 45}, Main.heapsort(new int[] {-8, 0, 4, 5, 6, 13})));
    }

    @Test
    void test_9_only_same_numbers() {
        assertTrue(Arrays.equals(new int[] {0, 0, 0, 0, 0}, Main.heapsort(new int[] {0, 0, 0, 0, 0})));
    }

    @Test
    void test_10_no_elements() {
        assertTrue(Arrays.equals(new int[] {}, Main.heapsort(new int[] {})));
    }

    @Test
    void test_11_heap_building() {
        int[] arr= new int[] {3, 4, 1, 2, 5};
        int n=arr.length;
        for (int j = n / 2; j >= 0; j--) {
            Main.build_heap(arr, j, n);
        }
        assertArrayEquals(new int[] {5, 4, 1, 2, 3},arr);
    }

    @Test
    void test_12_heap_building_wrong() {
        int[] arr = new int[] {3, 4, 1, 2, 5};
        int n = arr.length;
        for (int j = n / 2; j >= 0; j--) {
            Main.build_heap(arr, j, n);
        }
        assertFalse(Arrays.equals(new int[] {4, 2, 1, 5, 3}, arr));
    }

    @Test
    void test_13_heap_building_and_sort() {
        int[] arr = new int[] {3, 4, 1, 2, 5};
        assertArrayEquals(new int[] {4, 5, 1, 2, 3},Main.build_heap(arr, 0, arr.length));
    }
}
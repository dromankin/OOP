package ru.nsu.romankin;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void Test_1_positive_result() {
    assertArrayEquals(new int[] {1,2,3,4,5},Main.heapsort(new int[]{3,1,2,5,4}));
    }
    @Test
    void Test_2_negative_result (){
        assertFalse(Arrays.equals(new int[]{1,3,2,4,5}, Main.heapsort(new int[]{3, 1, 2, 5, 4})));
    }
    @Test
    void Test_3_negative_numbers(){
        assertArrayEquals(new int[] {-8,-5,0,1,12},Main.heapsort(new int[]{-5,-8,12,1,0}));
    }
    @Test
    void Test_4_negative_numbers_negative_result(){
        assertFalse(Arrays.equals(new int[] {12,-8,-5,1,0},Main.heapsort(new int[]{-5,-8,12,1,0})));
    }
    @Test
    void Test_5_repeating_numbers(){
        assertArrayEquals(new int[] {0, 1, 1, 1, 4, 5, 7, 7, 8, 87},Main.heapsort(new int[] {1,1,1,87,4,0,8,5,7,7}));
    }
    @Test
    void Test_6_one_element(){
        assertArrayEquals(new int[] {-8},Main.heapsort(new int[] {-8}));
    }
    @Test
    void Test_7_one_element_neg_result(){
        assertFalse(Arrays.equals(new int[] {-8},Main.heapsort(new int[] {0})));
    }
    @Test
    void Test_8_different_arrays(){
        assertFalse(Arrays.equals(new int[] {1,2,3,4,5,9,45},Main.heapsort(new int[] {-8,0,4,5,6,13})));
    }
    @Test
    void Test_9_only_same_numbers(){
        assertTrue(Arrays.equals(new int[] {0, 0, 0, 0, 0},Main.heapsort(new int[] {0, 0, 0, 0, 0})));
    }
    @Test
    void Test_10_no_elements(){
        assertTrue(Arrays.equals(new int[] {},Main.heapsort(new int[] {})));
    }
}
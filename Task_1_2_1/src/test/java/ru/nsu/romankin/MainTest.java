package ru.nsu.romankin;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void test() throws FileNotFoundException {
        Main.main(null);
        assertTrue(true);
    }
}
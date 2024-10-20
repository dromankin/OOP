package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void test() throws FileNotFoundException {
        Main.main(null);
        assertTrue(true);
    }
}
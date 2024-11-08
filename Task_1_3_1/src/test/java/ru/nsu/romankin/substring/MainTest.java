package ru.nsu.romankin.substring;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


class MainTest {

    @Test
    void searchTest() throws IOException {
        List<Integer> res = Main.search("test.txt", "lo");
        for (Integer elem : res) {
            assertEquals(elem % 11, 0);
        }
    }

    @Test
    void noSubstringTest() throws IOException {
        List<Integer> res = Main.search("test.txt", "");
        assertTrue(res.isEmpty());
    }

    @Test
    void substringLongerThanTextTest() throws IOException {
        List<Integer> res = Main.search("test.txt", "hello_worldl");
        assertTrue(res.isEmpty());
    }

    @Test
    void bigDataTest() throws IOException {
        File file = new File("bigData.txt");
        try (Writer bw = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            String substring = "abra";
            List<Integer> compareList = new ArrayList<>();
            int shift = 0;
            for (int i = 0; i < 2000000; i++) {
                bw.write("abrabrabra");
                bw.flush();
                compareList.add(shift);
                compareList.add(3 + shift);
                compareList.add(6 + shift);
                shift += 10;
            }
            List<Integer> res = Main.search("bigData.txt", substring);
            assertEquals(res, compareList);
        }
        file.delete();
    }
}
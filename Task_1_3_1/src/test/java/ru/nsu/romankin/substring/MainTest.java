package ru.nsu.romankin.substring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


class MainTest {

    @Test
    void searchTest() throws IOException {

        List<Long> res = Main.searchSubstring("test.txt", "lo");
        for (Long elem : res) {
            assertEquals(elem % 11, 0);
        }
    }

    @Test
    void noSubstringTest() throws IOException {
        List<Long> res = Main.searchSubstring("test.txt", "");
        assertTrue(res.isEmpty());
    }

    @Test
    void substringLongerThanTextTest() throws IOException {
        List<Long> res = Main.searchSubstring("test2.txt", "hello_worldl");
        assertTrue(res.isEmpty());
    }

    @Test
    void cyrillicTest() throws IOException {
        List<Long> res = Main.searchSubstring("cyrillicTest.txt", "ив");
        assertEquals(List.of(2L), res);
    }

    @Test
    void hieroglyphTest() throws IOException {
        List<Long> res = Main.searchSubstring("hieroglyphTest.txt", "好");
        assertEquals(List.of(1L, 6L), res);
    }

    @Test
    void crossStringsTest() throws IOException {
        List<Long> res = Main.searchSubstring("crossStringsTest.txt", "aaaaaaaaa");
        List<Long> compare = new ArrayList<>();
        for (long i = 0L; i <= 20L; i++) {
            compare.add(i);
        }
        assertEquals(compare, res);
    }

    @Test
    void bigDataTest() throws IOException {
        File file = new File("bigData.txt");
        try (Writer bw = new BufferedWriter(new FileWriter(file))) {
            file.createNewFile();
            String substring = "hello";

            for (long i = 0; i < 1000000000L; i++) {
                bw.append("abrabrabra");
                //bw.flush();
            }
            bw.write("hello_world");
            bw.flush();
            long resNum = 10000000000L;
            try (Reader br = new BufferedReader(new FileReader(file))) {
                List<Long> res = Main.search(br, substring);
                assertFalse(res.isEmpty());
                assertTrue(res.contains(resNum));
                assertEquals(res.size(), 1);
            }
        } finally {
            file.delete();
        }


    }

}
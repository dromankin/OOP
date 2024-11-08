package ru.nsu.romankin.substring;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Integer> search(String filename, String substring) throws IOException {
        List<Integer> res = new ArrayList<>();
        try (Reader br = new BufferedReader(new FileReader(filename))) {
            int subLength = substring.length();
            if (subLength == 0) {
                return res;
            }
            char[] buffer = new char[subLength];
            char symbol;
            int curIndex = 0;
            boolean eof = false;
            for (int i = 0; i < subLength; i++) {
                if ((symbol = (char) br.read()) <= 0) {
                    eof = true;
                    break;
                }
                buffer[i] = symbol;
            }
            curIndex += subLength;
            String string = new String(buffer);
            if (string.equals(substring)) {
                res.add(curIndex - subLength);
            }
            while (!eof) {
                if ((symbol = (char) br.read()) == '\uFFFF') {
                    //eof = true;
                    break;
                }
                curIndex++;
                for (int i = 0; i < subLength - 1; i++) {

                    buffer[i] = buffer[i + 1];
                }
                buffer[subLength - 1] = symbol;
                string = String.valueOf(buffer);
                if (string.equals(substring)) {
                    res.add(curIndex - subLength);
                }
            }
            return res;
        }
    }
    public static void main(String[] args) throws IOException {
        List<Integer> list = search("test.txt", "lo");
        for (Integer elem : list) {
            System.out.println(elem);
        }
    }
}
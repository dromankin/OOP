package ru.nsu.romankin.substring;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class, containing searching substring function.
 */
public class Main {

    /**
     * This function searching for entry indexes of a substring in a text.
     *
     * @param br - name of file with text
     *
     * @param substring - substring
     *                  
     * @return a list of integers
     */
    public static List<Long> search(Reader br, String substring) throws IOException {
        List<Long> res = new ArrayList<>();

        int subLength = substring.length();
        if (subLength == 0) {
            return res;
        }
        char[] buffer = new char[subLength];
        char symbol;
        long curIndex = 0;
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

    /**
     *
     * @param filename
     * @param substring
     * @return
     * @throws IOException
     */
    public static List<Long> searchSubstring(String filename, String substring)
            throws IOException {
        try (Reader br = new BufferedReader
                (new InputStreamReader(((Main.class.getResourceAsStream("/" + filename)))))) {
            return search(br, substring);
        }
    }

    /**
     * Main function.
     */
    public static void main(String[] args) throws IOException {

    }
}
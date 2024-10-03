package ru.nsu.romankin;

import java.util.Scanner;

/**main class to test parsing from console input.*/
public class Main {

    /**main function.*/
    public static void main(String[] args) throws Exception {
        String string = "(x+(x*(x+x)))";
        Parser parser = new Parser();
        Expression e = parser.readExpression(string);
        System.out.println(e.eval("x = 2"));
    }
}
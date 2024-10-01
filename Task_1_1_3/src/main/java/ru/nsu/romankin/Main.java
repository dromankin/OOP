package ru.nsu.romankin;

import java.util.Scanner;

/**main class to test parsing from console input.*/
public class Main {
    public static void main(String[] args) {
        String expr = "(2*(x+3))";
        //Scanner scanner = new Scanner(System.in);
        //String expr = scanner.nextLine();
        //System.out.println(string);
        Parser parser = new Parser();
        Expression e = parser.readExpression(expr);
        e.print();
        int evaluate = e.eval(e.eval_parse("x = 10; y = 14"));
        System.out.println();
        System.out.println();
        System.out.println(evaluate);
    }
}
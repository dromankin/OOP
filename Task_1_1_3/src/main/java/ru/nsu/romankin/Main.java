package ru.nsu.romankin;

public class Main {
    public static void main(String[] args) {
        String expr = "(x-y)";
        Parser parser = new Parser();
        Expression e = parser.readExpression(expr);
        e.print();
        int evaluate = e.eval(e.eval_parse("x = 10; y = 14"));
        System.out.println();
        System.out.println();
        System.out.println(evaluate);
    }
}
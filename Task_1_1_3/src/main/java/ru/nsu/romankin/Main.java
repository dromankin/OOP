package ru.nsu.romankin;

public class Main {
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        Expression de = e.derivative("x");
        int res = e.eval("x = 10; y = 13");
        e.print();
        System.out.println(res);
        de.print();
    }
}
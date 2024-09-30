package ru.nsu.romankin;

import java.util.Map;

public class Number extends Expression {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public void print() {
        System.out.print(value);
    }

    public int eval(Map<String, String> map) {
        return value;
    }

    public Expression derivative(String var) {
        return new Number(0);
    }
}

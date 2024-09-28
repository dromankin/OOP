package ru.nsu.romankin;

public class Number extends Expression {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public void print() {
        System.out.print(value);
    }

    public int eval(String variables) {
        return value;
    }

    public Expression derivative(String var) {
        return new Number(0);
    }
}

package ru.nsu.romankin;

import java.util.Map;

/**this subclass of Expression implements a number.*/
public class Number extends Expression {
    private int value;

    /**Class constructor.*/
    public Number(int value) {
        this.value = value;
    }

    /**prints the expression.*/
    public void print() {
        System.out.print(value);
    }

    /**evaluate the expression by HashMap with variables and values.*/
    public int eval(Map<String, String> map) {
        return value;
    }

    /**differentiates the expression.*/
    public Expression derivative(String var) {
        return new Number(0);
    }
}

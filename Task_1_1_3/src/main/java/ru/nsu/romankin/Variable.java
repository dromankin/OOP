package ru.nsu.romankin;

import java.util.Map;

public class Variable extends Expression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public void print() {
        System.out.print(variable);
    }

    public int eval(Map<String, String> map) {
        try {
            if (!map.containsKey(variable)) {
                throw  new Exception("No such variable");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Integer.parseInt(map.get(variable));

    }

    public Expression derivative(String var) {
        if (var.equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }

    }
}

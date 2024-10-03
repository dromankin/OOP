package ru.nsu.romankin;

import java.util.Map;

/**this subclass of Expression implements a variable.*/
public class Variable extends Expression {
    private String variable;

    /**Class constructor.*/
    public Variable(String variable) {
        this.variable = variable;
    }

    /**prints the expression.*/
    public void print() {
        System.out.print(variable);
    }

    /**evaluate the expression by HashMap with variables and values.*/
    public int eval(Map<String, String> map) throws Exception {

        if (!map.containsKey(variable)) {
            throw new Exception("No such variable");
        }

        return Integer.parseInt(map.get(variable));

    }

    /**differentiates the expression.*/
    public Expression derivative(String var) {
        if (var.equals(variable)) {
            return new Number(1);
        } else {
            return new Number(0);
        }

    }
}

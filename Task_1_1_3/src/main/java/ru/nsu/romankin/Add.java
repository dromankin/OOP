package ru.nsu.romankin;

import java.util.Map;

public class Add extends Expression {

    private Expression left;
    private Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("+");
        right.print();
        System.out.print(")");
    }

    public int eval(Map<String, String> map) {
        return left.eval(map) + right.eval(map);
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }
}

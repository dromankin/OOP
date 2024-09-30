package ru.nsu.romankin;

import java.util.Map;

public class Div extends Expression {

    private Expression left;
    private Expression right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("/");
        right.print();
        System.out.print(")");
    }

    public int eval(Map<String, String> map) {
        return left.eval(map) / right.eval(map);
    }

    public Expression derivative(String var) {
        return new Div (new Sub(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))),
                new Mul(right.derivative(var), right.derivative(var)));
    }
}

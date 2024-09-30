package ru.nsu.romankin;

import java.util.Map;

public class Sub extends Expression {

    private Expression left;
    private Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("-");
        right.print();
        System.out.print(")");
    }


    public int eval(Map<String, String> map) {
        return left.eval(map) - right.eval(map);
    }

    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }
}

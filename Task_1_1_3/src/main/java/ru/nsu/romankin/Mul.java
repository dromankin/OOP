package ru.nsu.romankin;

public class Mul extends Expression {

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("*");
        right.print();
        System.out.print(")");
    }

    public int eval(String variables) {
        return left.eval(variables) * right.eval(variables);
    }

    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var)));
    }
}

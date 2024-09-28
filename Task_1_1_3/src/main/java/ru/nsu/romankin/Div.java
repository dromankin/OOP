package ru.nsu.romankin;

public class Div extends Expression {

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

    public int eval(String variables) {
        return left.eval(variables) / right.eval(variables);
    }

    public Expression derivative(String var) {
        return new Div (new Sub(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))),
                new Mul(right.derivative(var), right.derivative(var)));
    }
}

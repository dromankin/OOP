package ru.nsu.romankin;

import java.util.Map;

/**this subclass of Expression implements division.*/
public class Div extends Expression {

    private Expression left; //left part of expression
    private Expression right; // right part of expression

    /**Class constructor.*/
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**prints the expression.*/
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("/");
        right.print();
        System.out.print(")");
    }

    /**evaluate the expression by HashMap with variables and values.*/
    public int eval(Map<String, String> map) {
        return left.eval(map) / right.eval(map);
    }

    /**differentiates the expression.*/
    public Expression derivative(String var) {
        return new Div (new Sub(new Mul(left.derivative(var), right),
                new Mul(left, right.derivative(var))),
                new Mul(right.derivative(var), right.derivative(var)));
    }
}

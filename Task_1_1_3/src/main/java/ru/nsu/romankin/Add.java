package ru.nsu.romankin;

import java.util.Map;

/**this subclass of Expression implements addition.*/
public class Add extends Expression {

    private Expression left; //left part of expression
    private Expression right; //right part of expression

    /**Class constructor.*/
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**prints the expression.*/
    public void print() {
        System.out.print("(");
        left.print();
        System.out.print("+");
        right.print();
        System.out.print(")");
    }

    /**evaluate the expression by HashMap with variables and values.*/
    public int eval(Map<String, String> map) {
        return left.eval(map) + right.eval(map);
    }

    /**differentiates the expression.*/
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }
}

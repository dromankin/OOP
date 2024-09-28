package ru.nsu.romankin;

public class Sub extends Expression {

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


    public int eval(String variables) {
        return left.eval(variables) - right.eval(variables);
    }

    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }
}

package ru.nsu.romankin;

public class Add extends Expression {

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

    public int eval(String vars) {
        return left.eval(vars) + right.eval(vars);
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }
}

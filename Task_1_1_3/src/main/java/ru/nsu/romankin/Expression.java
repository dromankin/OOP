package ru.nsu.romankin;

public abstract class Expression {
    private String expression;
    protected Expression left;
    protected Expression right;
    //public abstract Expression make_Expression();
    public abstract void print();
    public abstract int eval(String vars);
    public abstract Expression derivative(String var);
}

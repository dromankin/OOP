package ru.nsu.romankin;

public class Variable extends Expression {
    private String variable;

    public Variable(String variable) {
        this.variable = variable;
    }

    public void print() {
        System.out.print(variable);
    }

    public int eval(String string) {
        int start = 0;
        int res = 0;
        String var = "";
        String numberString = "";
        char c;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i))) {
                var += string.charAt(i);
            }
            if (string.charAt(i) == ' ') {
                if (var.equals(variable)) {
                    start = i + 3;
                    for (int j = start; j < string.length() && string.charAt(j) != ';'; j++) {
                        c = string.charAt(j);
                        numberString += c;
                        if (j == string.length() || string.charAt(j) == ';') {break;}
                    }
                }
                var = "";
            }
        }
        res = Integer.parseInt(numberString);
        return res;
    }

    public Expression derivative(String var) {
        if (var.equals(variable)) {
            return new Number(1);
        } else {
            return new Variable(variable);
        }

    }
}

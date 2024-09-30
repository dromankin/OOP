package ru.nsu.romankin;

import java.util.HashMap;
import java.util.Map;

public abstract class Expression {
    private String expression;

    //public abstract Expression make_Expression();
    public abstract void print();
    public abstract int eval(Map<String, String> map);
    public abstract Expression derivative(String var);
    //public abstract int evaluate(Map<String, String> map);
    public Map<String,String> eval_parse(String string) {
        Map<String, String> res = new HashMap<String, String>();
        String var = "";
        String number = "";
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i))) {
                var += string.charAt(i);
            }
            if (Character.isDigit(string.charAt(i)) || string.charAt(i) == '-') {
                number += string.charAt(i);
            }
            if (string.charAt(i) == ';' || i == string.length() - 1
                && !var.isEmpty() && !number.isEmpty()) {
                res.put(var, number);
                var = "";
                number = "";
            }
        }
        return res;
    }
}

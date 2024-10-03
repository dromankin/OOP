package ru.nsu.romankin;

import java.util.HashMap;
import java.util.Map;

/**This is abstract class describing expression and its methods implemented in subclasses.*/

public abstract class Expression {

    /**prints expression.*/
    public abstract void print();

    /**evaluate the expression by HashMap with pairs "variable:value".
     *
     * @param map - hashmap with "variable:value" pairs*/
    public abstract int eval(Map<String, String> map) throws Exception;

    /**evaluates the expression by the string.*/
    public int eval(String s) throws Exception {
        return eval(eval_parse(s));
    }
    /**differentiate the expression.*/
    public abstract Expression derivative(String var);

    /**this function parses the string with variables and values into hashmap.
     *
     * @param string - string that needs to be parsed*/
    public Map<String, String> eval_parse(String string) {
        Map<String, String> res = new HashMap<>();
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

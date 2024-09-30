package ru.nsu.romankin;

public class Parser {
    private static int pos = 0;
    String token;
    String text;
    private int oldpos;

    private boolean isVariable(String string) {
        return Character.isLetter(string.charAt(0));
    }

    String read_token(String string) {
        if (pos == string.length() - 1){
            token = "";
            return token;
        }
        if (string.charAt(pos) == '+' || string.charAt(pos) == '-' || string.charAt(pos) == '*' ||
                string.charAt(pos) == '/' || string.charAt(pos) == '(' ||
                string.charAt(pos) == ')') {
            token = "";
            token += string.charAt(pos);
            pos++;
            return token;
        }
        token = "";
        while (Character.isDigit(string.charAt(pos)) || Character.isLetter(string.charAt(pos))) {

            token += string.charAt(pos);
            pos++;
        }
        return token;
    }

    String peekToken(String string) {
        oldpos = pos;
        read_token(string);
        pos = oldpos;
        return token;
    }

    Expression parse_atom(String string) {
        if (peekToken(string).equals("(")) {
            read_token(string);
            Expression res = parse_expr(string);
            read_token(string);
            return res;
        }
        if (peekToken(string).equals("-")) {
            read_token(string);
            String s = read_token(string);
            if (isVariable(s)){
                return new Variable('-' + s);
            } else {
                return new Number(-Integer.parseInt(s));
            }
        } else {
            String s = read_token(string);
            if (isVariable(s)) {
                return new Variable(s);
            } else {
                return new Number(Integer.parseInt(s));
            }
        }
    }

    Expression parse_monome(String string) {
        Expression res = parse_atom(string);
        while (peekToken(string).equals("*") || peekToken(string).equals("/")) {
            String oper = read_token(string);
            Expression add = parse_atom(string);
            if (oper.equals("*")) {
                res = new Mul(res, add);
            }
            if (oper.equals("/")) {
                res = new Div(res, add);
            }
        }
        return res;
    }

    Expression parse_expr(String string) {
        Expression res = parse_monome(string);
        while (peekToken(string).equals("+") || peekToken(string).equals("-")) {
            String oper = read_token(string);
            Expression add = parse_monome(string);
            if (oper.equals("+")) {
                res = new Add(res, add);
            }
            if (oper.equals("-")) {
                res = new Sub(res, add);
            }
        }
        return res;
    }

    public Expression readExpression(String string) {
        Expression res = parse_expr(string);
        pos = 0;
        oldpos = 0;
        token = "";
        return res;
    }
}

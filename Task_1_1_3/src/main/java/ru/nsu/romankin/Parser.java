package ru.nsu.romankin;

/**this class implements functions that parse the string into Expression instance.*/

public class Parser {
    private static int pos = 0; //current position in the text
    private String token; //string containing the token
    private int oldpos; //old position for peekToken() function

    private boolean isVariable(String string) { //checks if string is variable
        return Character.isLetter(string.charAt(0));
    }

    private String read_token(String string) { //moves the pos pointer,
        // next time new token will be read
        if (pos == string.length() - 1) {
            token = "";
            return token;
        }
        if (string.charAt(pos) == '+' || string.charAt(pos) == '-' || string.charAt(pos) == '*'
                || string.charAt(pos) == '/' || string.charAt(pos) == '('
                || string.charAt(pos) == ')') {
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

    private String peekToken(String string) { //doesn't move the pos pointer,
        // next time the same token will be read
        oldpos = pos;
        read_token(string);
        pos = oldpos;
        return token;
    }

    private Expression parse_atom(String string) { //parses atoms - expressions in brackets
        if (peekToken(string).equals("(")) {
            read_token(string);
            Expression res = parse_expr(string);
            read_token(string);
            return res;
        }
        if (peekToken(string).equals("-")) {
            read_token(string);
            String s = read_token(string);
            if (isVariable(s)) {
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

    private Expression parse_monome(String string) { //parses monomes - groups of multiplications
        Expression res;
        res = parse_atom(string);
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

    private Expression parse_expr(String string) { //parses a sum of monomes
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

    /**final function that parses the expression and
     * sets pos, oldpos and token to zero for a new string.*/

    public Expression readExpression(String string) {
        Expression res = parse_expr(string);
        pos = 0;
        oldpos = 0;
        token = "";
        return res;
    }
}

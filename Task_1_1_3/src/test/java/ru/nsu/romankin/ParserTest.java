package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void parserTest() {
        String string = "(2+4+(x*(6-y))";
        Parser parser = new Parser();
        Expression e = parser.readExpression(string);
        int res = e.eval(e.eval_parse("x = 1; y = 2"));
        assertEquals(10, res);
    }

    @Test
    void allOperations() {
        String string = "(120/(8-(4*y-(30+x)))";
        Parser parser = new Parser();
        Expression e = parser.readExpression(string);
        int res = e.eval(e.eval_parse("x = -20; y = 2"));
        assertEquals(12, res);
    }

    @Test
    void multipleOperations() {
        String string = "(((5000/2)/25)/4)";
        Parser parser = new Parser();
        Expression e = parser.readExpression(string);
        int res = e.eval(e.eval_parse("x = -20; y = 2"));
        assertEquals(25, res);
    }
}
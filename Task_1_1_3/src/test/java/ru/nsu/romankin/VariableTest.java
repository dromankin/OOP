package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;


class VariableTest {

    @Test
    void constructorTest() {
        Variable var = new Variable("aBcDeF");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        var.print();
        String string = stream.toString();
        assertEquals(string, "aBcDeF");
        stream.reset();
    }

    @Test
    void evalTest() {
        Variable var = new Variable("x");
        int res = var.eval(var.eval_parse("y = 14; x = 5; t = 0"));
        assertEquals(5, res);
    }

    @Test
    void derivativeTest() {
        Variable var = new Variable("x");
        Expression de1 = var.derivative("x");
        Expression de2 = var.derivative("y");
        int res1 = de1.eval(de1.eval_parse("x = 2"));
        int res2 = de2.eval(de2.eval_parse("x = 2"));
        assertEquals(res1, 1);
        assertEquals(res2, 0);
    }
}
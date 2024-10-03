package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;


class MulTest {

    @Test
    void constructorTest() {
        Mul mul = new Mul(new Number(90), new Variable("x"));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        mul.print();
        String string = stream.toString();
        assertEquals(string, "(90*x)");
        stream.reset();
    }

    @Test
    void evalTest() throws Exception {
        Mul mul = new Mul(new Variable("x"), new Add(new Variable("y"), new Number(10)));
        int res = mul.eval("y = 14; x = 30");
        assertEquals(720, res);
    }

    @Test
    void derivativeTest() {
        Mul mul = new Mul(new Variable("z"), new Mul(new Variable("y"), new Variable("x")));
        Expression demul = mul.derivative("x");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        demul.print();
        String string = stream.toString();
        assertEquals("((0*(y*x))+(z*((0*x)+(y*1))))", string);
        stream.reset();
    }
}
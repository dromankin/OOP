package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class SubTest {

    @Test
    void constructorTest() {
        Sub sub = new Sub(new Number(90), new Variable("x"));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        sub.print();
        String string = stream.toString();
        assertEquals(string, "(90-x)");
        stream.reset();
    }

    @Test
    void evalTest() {
        Sub sub = new Sub(new Variable("x"), new Add(new Variable("y"), new Number(10)));
        int res = sub.eval(sub.eval_parse("y = 14; x = 30"));
        assertEquals(6, res);
    }

    @Test
    void derivativeTest() {
        Sub sub = new Sub(new Variable("z"), new Sub(new Variable("y"), new Variable("x")));
        Expression desub = sub.derivative("x");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        desub.print();
        String string = stream.toString();
        assertEquals("(0-(0-1))", string);
        stream.reset();
    }

}
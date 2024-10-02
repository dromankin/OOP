package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;


class DivTest {
    @Test
    void constructorTest() {
        Div div = new Div(new Number(90), new Variable("x"));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        div.print();
        String string = stream.toString();
        assertEquals(string, "(90/x)");
        stream.reset();
    }

    @Test
    void evalTest() {
        Div div = new Div(new Variable("x"), new Add(new Variable("y"), new Number(10)));
        int res = div.eval(div.eval_parse("y = 14; x = 30"));
        assertEquals(1, res);
    }

    @Test
    void derivativeTest() {
        Div div = new Div(new Variable("z"), new Mul(new Variable("y"), new Variable("x")));
        Expression dediv = div.derivative("x");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        dediv.print();
        String string = stream.toString();
        assertEquals("(((0*(y*x))-(z*((0*x)+(y*1))))/(((0*x)+(y*1))*((0*x)+(y*1))))", string);
        stream.reset();
    }
}
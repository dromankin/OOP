package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class AddTest {
    @Test
    void constructorTest() {
        Add add = new Add(new Number(90), new Variable("x"));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        add.print();
        String string = stream.toString();
        assertEquals(string, "(90+x)");
        stream.reset();
    }

    @Test
    void evalTest() throws Exception {
        Add add = new Add(new Variable("x"), new Add(new Variable("y"), new Number(10)));
        int res = add.eval("y = 14; x = 30");
        assertEquals(54, res);
    }

    @Test
    void derivativeTest() {
        Add add = new Add(new Variable("z"), new Add(new Variable("y"), new Variable("x")));
        Expression deadd = add.derivative("x");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        deadd.print();
        String string = stream.toString();
        assertEquals("(0+(0+1))", string);
        stream.reset();
    }
}
package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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
    void evalTest() throws Exception {
        Variable var = new Variable("x");
        int res = var.eval("y = 14; x = 5; t = 0");
        assertEquals(5, res);
    }

    @Test
    void derivativeTest() throws Exception {
        Variable var = new Variable("x");
        Expression de1 = var.derivative("x");
        Expression de2 = var.derivative("y");
        int res1 = de1.eval("x = 2");
        int res2 = de2.eval("x = 2");
        assertEquals(res1, 1);
        assertEquals(res2, 0);
    }

    @Test
    void exceptionTest() {
        Variable var = new Variable("x");
        try {
            int res = var.eval("y = 4");
            fail("No exception");
        } catch (Exception e) {
            assertNotNull(e);
        }
    }
}
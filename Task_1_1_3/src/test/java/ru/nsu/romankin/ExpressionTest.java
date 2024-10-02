package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

class ExpressionTest {

    @Test
    void printTest() {
        Expression e = new Add(new Number(178), new Add (new Variable("x"),
                new Add (new Variable("y"), new Number(100))));
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        e.print();
        String string = stream.toString();
        assertEquals("(178+(x+(y+100)))", string);
        assertNotEquals("178+x+y+100", string);
    }

    @Test
    void evalTest() {
        Expression e = new Add(new Number(4), new Sub(new Variable("x"), new Variable("y")));
        int res = e.eval(e.eval_parse("x = 10; z = 18; y = -8"));
        assertEquals(22, res);
    }

    @Test
    void evalParseTest() {
        Map<String, String> map = new HashMap<>();
        Expression e = new Number(1);
        map = e.eval_parse("x = 14; k = 8; z = 10; u = -8");
        assertTrue(map.containsKey("u") && (map.get("u").equals( "-8")));
        assertFalse(map.containsKey("x") && !map.get("x").equals("14"));
    }

    @Test
    void derivativeTest() {
        Expression e = new Mul(new Variable("y"), new Variable("x"));
        Expression de = e.derivative("x");
        Expression de2 = e.derivative("y");
        int res = de.eval(de.eval_parse("x = 14; y = 10"));
        int res2 = de2.eval(de2.eval_parse("x = 14; y = 10"));
        assertTrue(res == 10 && res2 == 14);
    }
}
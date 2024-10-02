package ru.nsu.romankin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import org.junit.jupiter.api.Test;


class NumberTest {

    @Test
    void constructorTest() {
        Number number = new Number(121);
        assertEquals(number.getValue(), 121);
    }

    @Test
    void printTest() {
        Number number = new Number(1234);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        number.print();
        String string = stream.toString();
        assertEquals("1234", string);
        stream.reset();
        Number number1 = new Number(-999999);
        number1.print();
        string = stream.toString();
        assertEquals(string, "-999999");
    }

    @Test
    void evalTest() {
        Number number = new Number(14);
        int res = number.eval(number.eval_parse("x = 10"));
        assertNotEquals(res, 10);
        assertEquals(res, 14);
    }

    @Test
    void derivativeTest() {
        Random random = new Random();
        int res = random.nextInt();
        Expression number = new Number(res);
        Expression deNumber = number.derivative("x");
        assertEquals(0, deNumber.eval(deNumber.eval_parse("x = 14")));
    }
}
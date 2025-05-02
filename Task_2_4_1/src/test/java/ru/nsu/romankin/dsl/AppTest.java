package ru.nsu.romankin.dsl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AppTest {
    @Test
    void mainTest() throws Exception {
        App.main(null);
        assertTrue(true);
    }

    @Test
    void gradeTest() {
        Marks m = new Marks();
        m.setPointsToFive(5);
        m.setPointsToFour(4);
        m.setPointsToThree(3);
        Student student = new Student();
        student.addPoints(1);
        student.setMark(m);
        assertSame(student.getGrade(), Grades.TWO);
        student.addPoints(1);
        student.setMark(m);
        assertSame(student.getGrade(), Grades.TWO);
        student.addPoints(1);
        student.setMark(m);
        assertSame(student.getGrade(), Grades.THREE);
        student.addPoints(1);
        student.setMark(m);
        assertSame(student.getGrade(), Grades.FOUR);
        student.addPoints(1);
        student.setMark(m);
        assertSame(student.getGrade(), Grades.FIVE);
    }

    @Test
    void getPointsTest() {
        PassResults pr = new PassResults(false, true);
        assertEquals(AnaliticUtils.getPoints(pr), 0.5);
        PassResults pr2 = new PassResults(false, false);
        assertEquals(AnaliticUtils.getPoints(pr2), 0);
        PassResults pr3 = new PassResults(true, true);
        assertEquals(AnaliticUtils.getPoints(pr3), 1);
    }


}
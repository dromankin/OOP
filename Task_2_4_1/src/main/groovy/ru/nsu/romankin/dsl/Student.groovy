package ru.nsu.romankin.dsl

class Student {
    String username;
    String name;
    String repository;
    double points = 0;
    Grades grade
    void addPoints(double num) {
        points += num
    }

    void setMark(Marks m) {
        if (points < m.pointsToThree) {
            grade = Grades.TWO
        } else if (m.pointsToThree <= points && points < m.pointsToFour) {
            grade = Grades.THREE
        } else if (m.pointsToFour <= points && points < m.pointsToFive) {
            grade = Grades.FOUR
        } else {
            grade = Grades.FIVE
        }
    }
}

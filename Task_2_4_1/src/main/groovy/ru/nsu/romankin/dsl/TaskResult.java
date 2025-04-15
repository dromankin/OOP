package ru.nsu.romankin.dsl;

public record TaskResult(
        Student student,
        boolean builds,
        int testCount,
        int failCount,
        int skipCount,
        int coverage,
        double points,
        CheckstyleResult checkstyle,
        boolean softPass,
        boolean hardPass
) {}
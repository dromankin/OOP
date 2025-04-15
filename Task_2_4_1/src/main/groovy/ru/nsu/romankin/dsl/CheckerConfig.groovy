package ru.nsu.romankin.dsl

import org.codehaus.groovy.control.CompilerConfiguration

class CheckerConfig {
    ArrayList<Task> tasks = null
    ArrayList<Student> students = null

    private void task(Closure c) {
        if (!tasks) {
            tasks = new ArrayList<>()
        }
        Task task = new Task()
        c.setDelegate(task)
        c.setResolveStrategy(Closure.DELEGATE_FIRST)
        c.call()
        tasks.add(task)
    }


    private void student(Closure c) {
        if (!students) {
            students = new ArrayList<>()
        }
        var s = new Student()
        c.setDelegate(s)
        c.setResolveStrategy(Closure.DELEGATE_FIRST)
        c.call()
        students.add(s)
    }

}

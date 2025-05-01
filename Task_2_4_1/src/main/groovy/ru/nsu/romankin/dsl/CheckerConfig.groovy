package ru.nsu.romankin.dsl


class CheckerConfig {
    List<Task> tasks = new ArrayList<>()
    List<Student> students = new ArrayList<>()
    List<Group> groups = new ArrayList<>()
    Marks marks = new Marks()

    void student(Closure c) {

        Student s = new Student()
        c.setDelegate(s)
        c.setResolveStrategy(Closure.DELEGATE_FIRST)
        c.call()
        students.add(s)
    }

     void task(Closure c) {

        Task task = new Task()
        c.setDelegate(task)
        c.setResolveStrategy(Closure.DELEGATE_FIRST)
        c.call()
        tasks.add(task)
    }


    void group(Closure c) {
        Group g = new Group()
        c.setDelegate(g)
        c.setResolveStrategy(Closure.DELEGATE_FIRST)
        c.call()
        groups.add(g)
    }

    void pointsToMark(Closure c) {
        Marks m = new Marks()
        c.setDelegate(m)
        c.setResolveStrategy(Closure.DELEGATE_FIRST)
        c.call()
        marks = m
    }

}

import ru.nsu.romankin.dsl.Student
import ru.nsu.romankin.dsl.Task
import ru.nsu.romankin.dsl.Group

import java.time.LocalDate



task {
    id = "Task_2_1_1"
    softDeadline = LocalDate.of(2025, 2, 14)
    hardDeadline = LocalDate.of(2025, 2, 21)
    points = 1
}

task {
    id = "Task_2_2_1"
    softDeadline = LocalDate.of(2025, 3, 7)
    hardDeadline = LocalDate.of(2025, 3, 14)
    points = 1
}

task {
    id = "Task_2_3_1"
    softDeadline = LocalDate.of(2025, 3, 28)
    hardDeadline = LocalDate.of(2025, 4, 4)
    points = 1
}

student {
    username = "dromankin"
    name = "Daniil Romankin"
    repository = "https://github.com/dromankin/OOP"
}

student {
    username = "AYETATAPIN"
    name = "Dmitry Demidov"
    repository = "https://github.com/AYETATAPIN/OOP"
}

student {
    username = "ArtemChepenkov"
    name = "Artem Chepenkov"
    repository = "https://github.com/ArtemChepenkov/OOP"
}



group {
    name = "23214"
    groupStudents = [
            students[0],
            students[1]
    ]
}

group {
    name = "23213"
    groupStudents = [
            students[2]
    ]
}

pointsToMark {
    pointsToFive = 5
    pointsToFour = 4
    pointsToThree = 3
}

/*
task {
    id = "Task_2_3_1"
    softDeadline = LocalDate.of(2025, 3, 28)
    hardDeadline = LocalDate.of(2025, 4, 4)
    points = 1
}
*/
/*
task {
    id = "Task_2_4_1"
    softDeadline = LocalDate.of(2025, 4, 18)
    hardDeadline = LocalDate.of(2025, 5, 2)
    points = 1
}
*/


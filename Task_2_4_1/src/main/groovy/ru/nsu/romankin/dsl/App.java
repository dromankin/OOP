package ru.nsu.romankin.dsl;


import static ru.nsu.romankin.dsl.AnaliticUtils.getCheckstyleResult;
import static ru.nsu.romankin.dsl.AnaliticUtils.getCoveragePercentage;
import static ru.nsu.romankin.dsl.AnaliticUtils.getPoints;
import static ru.nsu.romankin.dsl.AnaliticUtils.getSoftHardPasses;
import static ru.nsu.romankin.dsl.AnaliticUtils.getTestCounts;
import static ru.nsu.romankin.dsl.GitUtils.updateStudentsRepos;
import static ru.nsu.romankin.dsl.IoUtils.generateReport;
import static ru.nsu.romankin.dsl.IoUtils.runTask;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;


/**
 * Main app class.
 */
public class App {
    public static void main(String[] args) throws Exception {
        CompilerConfiguration cc = new CompilerConfiguration();
        String classpath = "build/classes/groovy/main";
        cc.setScriptBaseClass(DelegatingScript.class.getName());
        cc.setClasspath(classpath);
        ClassLoader loader = App.class.getClassLoader();
        GroovyShell sh = new GroovyShell(loader, new Binding(), cc);

        InputStream inputStream = loader.getResourceAsStream("config.groovy");
        if (inputStream == null) {
            System.out.println("Error: No config found.");
            return;
        }
        DelegatingScript script = (DelegatingScript) sh.parse(
                new BufferedReader(new InputStreamReader(inputStream))
        );
        CheckerConfig config = new CheckerConfig();
        script.setDelegate(config);
        script.run();
        String repoPrefix = "repoes";
        GradleConnector connector = GradleConnector.newConnector();
        ArrayList<ArrayList<TaskResult>> results = new ArrayList<>();

        for (Group group : config.getGroups()) {
            for (Student student : group.getGroupStudents()) {
                System.out.println(group.getName() + " " + student.getName());
            }
        }


        if (updateStudentsRepos(config, repoPrefix)) {
            return;
        }

        for (Task task : config.getTasks()) {
            ArrayList<TaskResult> taskResults = new ArrayList<>();
            for (Student student : config.getStudents()) {
                System.out.println("Checking task " + task.getId() + " " + student.getUsername());
                File projectFile = new File(
                        String.format("%s/%s/%s", repoPrefix, student.getUsername(), task.getId())
                );

                if (!projectFile.exists()) {
                    System.out.println("Project directory not found");
                    continue;
                }

                ProjectConnection connection = connector.forProjectDirectory(projectFile)
                        .connect();
                runTask(connection, new TaskRunConfig("clean"));
                boolean builds = runTask(connection, new TaskRunConfig("build").withExcludeTests());
                boolean tests = runTask(connection, new TaskRunConfig("test"));
                builds = builds;
                TestCounts counts = getTestCounts(repoPrefix, student, task);
                counts = counts;
                int coveragePercent = 0;
                if (tests) {
                    coveragePercent = getCoveragePercentage(connection, repoPrefix, student, task);
                }

                Checkstyle checkstyle = getCheckstyleResult(task, student, repoPrefix);

                runTask(connection, new TaskRunConfig("javadoc"));

                PassResults passes = getSoftHardPasses(task, student, repoPrefix);
                double points = getPoints(passes);
                student.addPoints(points);
                taskResults.add(
                        new TaskResult(
                                student,
                                builds,
                                counts.total(),
                                counts.fail(),
                                counts.skip(),
                                coveragePercent,
                                points,
                                checkstyle,
                                passes.getSoft(),
                                passes.getHard()
                        )
                );
                connection.close();
            }
            results.add(taskResults);
        }

        for (Student student : config.getStudents()) {
            student.setMark(config.getMarks());
        }

        generateReport(results, config);
    }


}
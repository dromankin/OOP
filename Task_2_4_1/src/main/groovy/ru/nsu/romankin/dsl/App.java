package ru.nsu.romankin.dsl;


import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.DelegatingScript;
import org.codehaus.groovy.control.CompilerConfiguration;

import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;


import java.io.*;

import java.util.*;

import static ru.nsu.romankin.dsl.Utils.*;

public class App {
    public static void main(String[] args) throws Exception {
        CompilerConfiguration cc = new CompilerConfiguration();
        cc.setScriptBaseClass(DelegatingScript.class.getName());
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

                TestCounts counts = getTestCounts(repoPrefix, student, task);

                int coveragePercent = 0;
                if (tests) {
                    coveragePercent = getCoveragePercentage(connection, repoPrefix, student, task);
                }

                Checkstyle checkstyle = getCheckstyleResult(task, student, repoPrefix);

                runTask(connection, new TaskRunConfig("javadoc"));

                PassResults passes = getSoftHardPasses(task, student, repoPrefix);
                double points = getPoints(passes);
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
        System.out.println(results);

        generateReport(results, config);
    }


}
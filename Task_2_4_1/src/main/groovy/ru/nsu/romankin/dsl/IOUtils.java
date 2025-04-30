package ru.nsu.romankin.dsl;

import com.puppycrawl.tools.checkstyle.Main;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.ProjectConnection;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;

public class IOUtils {

    public static boolean runTask(ProjectConnection conn, TaskRunConfig config) {
        try {
            System.out.printf("Running %s...", config.task());
            BuildLauncher builder = conn.newBuild().forTasks(config.task());
            if (config.excludeTests()) {
                builder = builder.addArguments("-x",  "test");
            }
            builder.run();
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Failure: " + e);
            return false;
        }
        return true;
    }

    public static void generateReport(
            ArrayList<ArrayList<TaskResult>> results,
            CheckerConfig config
    ) throws IOException, URISyntaxException {
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(new FileTemplateResolver());
        Context ctx = new Context();
        ctx.setVariable("results", results);
        ctx.setVariable("tasks", config.getTasks());
        ctx.setVariable("groups", config.getGroups());
        File report = new File("report.html");
        URL res = IOUtils.class.getClassLoader().getResource("reportTemplate.html");
        File file = Paths.get(res.toURI()).toFile();
        String text = file.getAbsolutePath();
        try (FileOutputStream writer = new FileOutputStream(report)) {
            String result = engine.process(text, ctx);
            writer.write(result.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("Failed to write report: " + e);
        }
    }
}

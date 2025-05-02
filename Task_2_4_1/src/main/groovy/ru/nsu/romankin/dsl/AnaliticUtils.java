package ru.nsu.romankin.dsl;



import static com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit;

import com.puppycrawl.tools.checkstyle.Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.lang3.BooleanUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.gradle.tooling.ProjectConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

/**
 * Class for analitic utils(calculating points, getting coverage percentage etc.).
 */
public class AnaliticUtils {

    /**
     * Method for calculating points by pass results.
     */
    public static double getPoints(PassResults passResults) {
        return 0.5 * (BooleanUtils.toInteger(passResults.getHard())
                + BooleanUtils.toInteger(passResults.getSoft()));
    }

    /**
     * Method checking whether student pass hard and soft deadlines or not.
     */
    public static PassResults getSoftHardPasses(Task task, Student student, String repoPrefix)
            throws IOException, GitAPIException {
        boolean hardPass = false;
        boolean softPass = false;
        hardPass = hardPass;
        softPass = softPass;
        File repoFile = new File(String.format("%s/%s", repoPrefix, student.getUsername()));
        Iterable<RevCommit> commits = Git
                .open(repoFile)
                .log()
                .addPath(task.getId())
                .call();
        LocalDate first = null;
        int last;
        LocalDate lastDate = null;
        Iterator<RevCommit> iterator = commits.iterator();
        List<RevCommit> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        Collections.reverse(list);
        for (RevCommit commit : list) {
            if (first == null) {
                first = LocalDate.ofInstant(
                        Instant.ofEpochSecond(commit.getCommitTime()), ZoneId.systemDefault()
                );
            }
            last = commit.getCommitTime();
            lastDate = LocalDate.ofInstant(Instant.ofEpochSecond(last), ZoneId.systemDefault());
        }
        if (first == null) {
            System.out.println("No commits with this project found.");
            return new PassResults(false, false);
        }

        softPass = first.isBefore(task.getSoftDeadline())
                || first.isEqual(task.getHardDeadline());
        hardPass = lastDate.isBefore(task.getHardDeadline())
                || lastDate.isEqual(task.getHardDeadline());
        return new PassResults(softPass, hardPass);
    }

    /**
     * Method for checkstyle result.
     */
    public static Checkstyle getCheckstyleResult(
            Task task,
            Student student,
            String repoPrefix
    ) throws Exception {
        Checkstyle checkstyle = Checkstyle.CLEAN;

        String outFile = "checkstyle.txt";
        int exitCode = catchSystemExit(() -> {

            String configPath = String.format(
                    "%s/%s/.github/google_checks.xml",
                    repoPrefix, student.getUsername()
            );
            String mainSourcePath = String.format(
                    "%s/%s/%s/src/main/java/",
                    repoPrefix, student.getUsername(), task.getId()
            );
            String testSourcePath = String.format(
                    "%s/%s/%s/src/test/java/",
                    repoPrefix, student.getUsername(), task.getId()
            );
            Main.main("-c", configPath, "-o", outFile, mainSourcePath, testSourcePath);

        });

        int warnCount = 0;
        try (FileInputStream reader = new FileInputStream(outFile)) {
            Scanner scanner = new Scanner(reader);
            int cnt = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                cnt += 1;
            }
            warnCount = cnt - 2;
        } catch (Exception e) {
            System.out.println("Checkstyle output read error" + e);
        }
        if (exitCode != 0) {
            checkstyle = Checkstyle.ERROR;
        } else if (warnCount > 0) {
            checkstyle = Checkstyle.WARNING;
        }

        return checkstyle;
    }

    /**
     * Method for calculating coverage percentage.
     */
    public static int getCoveragePercentage(
            ProjectConnection connection,
            String repoPrefix,
            Student student,
            Task task
    ) {
        IoUtils.runTask(connection, new TaskRunConfig("jacocoTestReport"));

        File jacocoFile = new File(
                String.format(
                        "%s/%s/%s/build/reports/jacoco/test/jacocoTestReport.xml",
                        repoPrefix,
                        student.getUsername(),
                        task.getId()
                )
        );

        try {
            Document report = Jsoup.parse(jacocoFile, "UTF-8", "", Parser.xmlParser());
            report.getElementsByTag("package").remove();
            float total = 0.0f;
            for (Element el : report.getElementsByTag("counter")) {
                int cov = Integer.parseInt(el.attribute("covered").getValue());
                int miss = Integer.parseInt(el.attribute("missed").getValue());
                total += (float) cov / (cov + miss);
            }
            float coverage = total / report.getElementsByTag("counter").size();
            return Math.round(coverage * 100.0f);
        } catch (IOException e) {
            System.out.println("Failed to find jacoco test report: " + e);
            return 0;
        }
    }

    /**
     * Method for getting test counts.
     */
    public static TestCounts getTestCounts(String repoPrefix, Student student, Task task) {
        File reportFile = new File(
                String.format(
                        "%s/%s/%s/build/reports/tests/test/index.html",
                        repoPrefix,
                        student.getUsername(),
                        task.getId()
                )
        );
        try {
            Document report = Jsoup.parse(reportFile, "UTF-8");
            int testCount = Integer.parseInt(
                    report.getElementById("tests").getElementsByClass("counter").get(0).text()
            );
            int failCount = Integer.parseInt(
                    report.getElementById("failures").getElementsByClass("counter").get(0).text()
            );
            int skipCount = Integer.parseInt(
                    report.getElementById("ignored").getElementsByClass("counter").get(0).text()
            );
            return new TestCounts(testCount, failCount, skipCount);
        } catch (IOException e) {
            System.out.println("Failed to find test report file: " + reportFile);
            return new TestCounts(0, 0, 0);
        }
    }
}

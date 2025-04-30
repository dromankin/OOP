package ru.nsu.romankin.dsl;

import org.eclipse.jgit.api.Git;

import java.io.File;

public class GitUtils {

    public static boolean updateStudentsRepos(CheckerConfig config, String repoPrefix) {
        for (Student student : config.getStudents()) {
            try {
                File repoFile = new File(String.format("%s/%s", repoPrefix, student.getUsername()));
                Git repo;
                if (!repoFile.exists()) {
                    repo = Git.cloneRepository()
                            .setURI(String.format("https://github.com/%s/OOP.git", student.getUsername()))
                            .setDirectory(repoFile)
                            .call();
                } else {
                    try {
                        repo = Git.open(repoFile);
                    } catch (Exception e) {
                        System.out.println("No repo");
                        return true;
                    }
                }
                if (repo != null) {
                    repo.pull().call();
                }
            } catch (Exception e) {
                System.out.println("Failed to clone: " + e);
            }
        }
        return false;
    }
}

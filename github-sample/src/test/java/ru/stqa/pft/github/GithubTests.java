package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("1ff922e8f51cca00ecac5a82b4bc0d5849e4b5ac");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("JuliaStuk", "java_pft")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build()))
            System.out.println(new RepoCommit.Smart(commit).message());

    }
}

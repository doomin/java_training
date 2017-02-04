package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by root on 2/4/17.
 */
public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("e722b2cdd8e28342a1fea58ee94b7ca6500388a8");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("doomin", "java_training")).commits();

        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
                    System.out.println(new RepoCommit.Smart(commit).message());
        }
    }

}

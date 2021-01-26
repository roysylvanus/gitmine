package com.example.searchgitapp.Model;

import java.util.List;

public class GitHubSearch {

    List<GitHubRep> gitHubRepsList;

    public GitHubSearch(List<GitHubRep> gitHubRepsList) {
        this.gitHubRepsList = gitHubRepsList;
    }

    public GitHubSearch(){}

    public List<GitHubRep> getGitHubRepsList() {
        return gitHubRepsList;
    }

    public void setGitHubRepsList(List<GitHubRep> gitHubRepsList) {
        this.gitHubRepsList = gitHubRepsList;
    }
}

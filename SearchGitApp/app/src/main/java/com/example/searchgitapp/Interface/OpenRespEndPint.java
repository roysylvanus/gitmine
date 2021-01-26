package com.example.searchgitapp.Interface;

import com.example.searchgitapp.Model.GitHubRep;
import com.example.searchgitapp.Model.GitHubSearch;
import com.example.searchgitapp.Model.GithubCode;
import com.example.searchgitapp.Model.GithubCommit;
import com.example.searchgitapp.Model.GithubIssues;
import com.example.searchgitapp.Model.GithubLabel;
import com.example.searchgitapp.Model.GithubRespo;
import com.example.searchgitapp.Model.GithubTopic;
import com.example.searchgitapp.Model.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  OpenRespEndPint {

    @GET("search/repositories?")
    Call<GithubRespo> getOpenRepositories(@Query("q") String searchName);

    @GET("search/topics?")
    Call<GithubTopic> getTopics(@Query("q") String searchTopic);


    @GET("search/users")
    Call<GithubUser> getUser(@Query("q") String user);

    @GET("search/labels")
    Call<GithubLabel> getLabels(@Query("q") String labels);

    @GET("search/issues")
    Call<GithubIssues> getIssues(@Query("q") String issues);

    @GET("search/commits")
    Call<GithubCommit> getCommits(@Query("q") String commits);

    @GET("search/code")
    Call<GithubCode> getCode(@Query("q") String commits);
}

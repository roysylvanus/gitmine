package com.example.searchgitapp.Model;

import com.google.gson.annotations.SerializedName;

public class GitHubRep {

    @SerializedName("full_name")
    public String fullName;
    @SerializedName("description")
    public String description;
    @SerializedName("html_url")
    public String html_url;
    @SerializedName("followers_url")
    public String followers_url;
    @SerializedName("following_url")
    public String following_url;
    @SerializedName("name")
    public String name;
    @SerializedName( "avatar_url")
    public String avatar_url;



    public GitHubRep(){

    }

    public GitHubRep(String fullName, String description, String html_url, String followers_url, String following_url, String name, String avatar_url) {
        this.fullName = fullName;
        this.description = description;
        this.html_url = html_url;
        this.followers_url = followers_url;
        this.following_url = following_url;
        this.name = name;
        this.avatar_url = avatar_url;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getFollowing_url() {
        return following_url;
    }

    public void setFollowing_url(String following_url) {
        this.following_url = following_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}


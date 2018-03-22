
package com.sansara.develop.issuesofgitrepository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Issue {

    @SerializedName("comments_url")
    @Expose
    private String commentsUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("labels")
    @Expose
    private List<Label> labels = null;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("body")
    @Expose
    private String body;

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}

class Label {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("color")
    @Expose
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}

class User {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

}
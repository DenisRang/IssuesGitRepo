package com.sansara.develop.issuesofgitrepository.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by den on 25.03.2018.
 */

public class Comment {
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("body")
    @Expose
    private String body;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}

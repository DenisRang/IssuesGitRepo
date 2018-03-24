
package com.sansara.develop.issuesofgitrepository;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class Issue implements Parcelable {
    private static final String LOG_TAG = Issue.class.getSimpleName();

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

    public String getFormattedDateCreatedAt() {
        return getFormattedDate(createdAt);
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

    private String getFormattedDate(String inDateString) {
        String outDateString = null;
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat("y-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            SimpleDateFormat outFormat = new SimpleDateFormat("dd.MM.y'\n'HH:mm", Locale.US);

            Date outDate = inFormat.parse(inDateString);
            outDateString = outFormat.format(outDate);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Error with parsing date");
            e.printStackTrace();
        }
        return outDateString;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(commentsUrl);
        out.writeString(title);
        out.writeString(state);
        out.writeString(createdAt);
        out.writeString(body);

        out.writeParcelable(user, flags);

        out.writeTypedList(labels);
    }

    private Issue(Parcel in) {
        this();
        commentsUrl = in.readString();
        title = in.readString();
        state = in.readString();
        createdAt = in.readString();
        body = in.readString();

        user = in.readParcelable(User.class.getClassLoader());

        in.readTypedList(labels, Label.CREATOR);
    }

    private Issue() {
        labels = new ArrayList<Label>();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Issue> CREATOR
            = new Parcelable.Creator<Issue>() {

        @Override
        public Issue createFromParcel(Parcel in) {
            return new Issue(in);
        }

        @Override
        public Issue[] newArray(int size) {
            return new Issue[size];
        }
    };
}

class Label implements Parcelable {

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

    public String getFormattedColor() {
        return "#" + color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(color);
    }

    private Label(Parcel in) {
        name = in.readString();
        color = in.readString();
    }

    private Label() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Label> CREATOR
            = new Parcelable.Creator<Label>() {

        @Override
        public Label createFromParcel(Parcel in) {
            return new Label(in);
        }

        @Override
        public Label[] newArray(int size) {
            return new Label[size];
        }
    };

}

class User implements Parcelable {

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

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(login);
        out.writeString(avatarUrl);
    }

    private User(Parcel in) {
        login = in.readString();
        avatarUrl = in.readString();
    }

    private User() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
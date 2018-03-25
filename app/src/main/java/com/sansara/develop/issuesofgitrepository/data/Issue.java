
package com.sansara.develop.issuesofgitrepository.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Issue implements Parcelable {
    private static final String LOG_TAG = Issue.class.getSimpleName();

    @SerializedName("number")
    @Expose
    private Integer number;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        out.writeInt(number);
        out.writeString(title);
        out.writeString(state);
        out.writeString(createdAt);
        out.writeString(body);

        out.writeParcelable(user, flags);

        out.writeTypedList(labels);
    }

    private Issue(Parcel in) {
        this();
        number = in.readInt();
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





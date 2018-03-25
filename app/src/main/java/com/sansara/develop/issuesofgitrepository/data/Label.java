package com.sansara.develop.issuesofgitrepository.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by den on 25.03.2018.
 */

public class Label implements Parcelable {

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
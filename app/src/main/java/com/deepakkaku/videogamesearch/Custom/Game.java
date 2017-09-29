package com.deepakkaku.videogamesearch.Custom;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Deepak Kaku on 28-09-2017.
 */

public class Game {

    @SerializedName("name")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("image")
    @Expose
    private Images image;

    protected Game(Parcel in) {
        title = in.readString();
        description = in.readString();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Images getImage() {
        return image;
    }


}

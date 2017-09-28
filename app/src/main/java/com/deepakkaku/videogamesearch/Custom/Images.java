package com.deepakkaku.videogamesearch.Custom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Deepak Kaku on 28-09-2017.
 */

public class Images {


    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;

    @SerializedName("super_url")
    @Expose
    private String superUrl;

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getSuperUrl() {
        return superUrl;
    }

    public void setScreenUrl(String superUrl) {
        this.superUrl = superUrl;
    }
}

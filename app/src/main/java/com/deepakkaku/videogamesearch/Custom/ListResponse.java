package com.deepakkaku.videogamesearch.Custom;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Deepak Kaku on 28-09-2017.
 */

public class ListResponse {


    @SerializedName("results")
    @Expose
    private ArrayList<Game> gameList;

    public ArrayList<Game> getGameList() {
        return gameList;
    }





}

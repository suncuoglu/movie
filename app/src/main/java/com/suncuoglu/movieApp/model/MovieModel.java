package com.suncuoglu.movieApp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieModel {

    @SerializedName("Search")
    @Expose
    public
    ArrayList<Search> searchArrayList;

    public ArrayList<Search> getSearchArrayList() {
        return searchArrayList;
    }

    public void setSearchArrayList(ArrayList<Search> searchArrayList) {
        this.searchArrayList = searchArrayList;
    }
}


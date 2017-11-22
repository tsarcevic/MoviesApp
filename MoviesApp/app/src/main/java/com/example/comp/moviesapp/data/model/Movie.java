package com.example.comp.moviesapp.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by COMP on 16.11.2017..
 */

public class Movie extends RealmObject {

    private int id;
    @SerializedName("original_title")
    private String title;
    @SerializedName("release_date")
    private int year;
    private int runtime;
    @SerializedName("overview")
    private String plot;
    @SerializedName("poster_path")
    private String poster;
    private int vote_average;
    private int revenue;

    public Movie() {
    }

    public Movie(int id, String title, int year, int runtime, String plot, String poster, int vote_average, int revenue) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.plot = plot;
        this.poster = poster;
        this.vote_average = vote_average;
        this.revenue = revenue;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getPlot() {
        return plot;
    }

    public String getPoster() {
        return poster;
    }

    public int getVote_average() {
        return vote_average;
    }

    public int getRevenue() {
        return revenue;
    }
}

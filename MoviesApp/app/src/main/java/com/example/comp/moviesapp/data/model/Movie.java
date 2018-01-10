package com.example.comp.moviesapp.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

import static com.example.comp.moviesapp.utils.StringUtils.isStringEmpty;

/**
 * Created by COMP on 16.11.2017..
 */

public class Movie extends RealmObject {

    private int id;
    private String title;
    @SerializedName("release_date")
    private String year;
    @SerializedName("overview")
    private String plot;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("vote_average")
    private double voteAverage;

    public Movie() {
    }

    public Movie(int id, String title, String year, String plot, String poster, double voteAverage) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.poster = poster;
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return isStringEmpty(title);
    }

    public String getYear() {
        return isStringEmpty(year);
    }

    public String getPlot() {
        return isStringEmpty(plot);
    }

    public String getPoster() {
        return isStringEmpty(poster);
    }

    public double getVoteAverage() {
        return voteAverage;
    }
}

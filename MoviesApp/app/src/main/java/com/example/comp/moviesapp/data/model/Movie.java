package com.example.comp.moviesapp.data.model;

import com.example.comp.moviesapp.utils.StringUtils;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by COMP on 16.11.2017..
 */

public class Movie extends RealmObject {

    @SerializedName("imbdID")
    private String id;
    @SerializedName("Title")
    private String title;
    @SerializedName("Year")
    private int year;
    @SerializedName("Runtime")
    private String runtime;
    @SerializedName("Genre")
    private String genre;
    @SerializedName("Plot")
    private String plot;
    @SerializedName("Actors")
    private String actors;
    @SerializedName("Poster")
    private String poster;
    private int imbdRating;
    @SerializedName("BoxOffice")
    private String boxOffice;
    @SerializedName("Production")
    private String production;

    private boolean watched;

    public Movie(String id, String title, int year, String runtime, String genre, String plot, String actors, String poster, int imbdRating, String boxOffice, String production) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.genre = genre;
        this.plot = plot;
        this.actors = actors;
        this.poster = poster;
        this.imbdRating = imbdRating;
        this.boxOffice = boxOffice;
        this.production = production;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public String getId() {
        return StringUtils.getValueOrEmpty(id);
    }

    public String getTitle() {
        return StringUtils.getValueOrEmpty(title);
    }

    public int getYear() {
        return year;
    }

    public String getRuntime() {
        return StringUtils.getValueOrEmpty(runtime);
    }

    public String getGenre() {
        return StringUtils.getValueOrEmpty(genre);
    }

    public String getPlot() {
        return StringUtils.getValueOrEmpty(plot);
    }

    public String getActors() {
        return StringUtils.getValueOrEmpty(actors);
    }

    public String getPoster() {
        return StringUtils.getValueOrEmpty(poster);
    }

    public int getImbdRating() {
        return imbdRating;
    }

    public String getBoxOffice() {
        return StringUtils.getValueOrEmpty(boxOffice);
    }

    public String getProduction() {
        return StringUtils.getValueOrEmpty(production);
    }
}

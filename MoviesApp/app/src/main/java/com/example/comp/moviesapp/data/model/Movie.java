package com.example.comp.moviesapp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by COMP on 16.11.2017..
 */

public class Movie {

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

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlot() {
        return plot;
    }

    public String getActors() {
        return actors;
    }

    public String getPoster() {
        return poster;
    }

    public int getImbdRating() {
        return imbdRating;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public String getProduction() {
        return production;
    }
}

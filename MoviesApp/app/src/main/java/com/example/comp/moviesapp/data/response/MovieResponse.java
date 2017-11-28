package com.example.comp.moviesapp.data.response;

import com.example.comp.moviesapp.data.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 16.11.2017..
 */

public class MovieResponse {

    private int page;
    @SerializedName("results")
    private List<Movie> moviesList = new ArrayList<>();

    public int getTotalResults() {
        return page;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }
}

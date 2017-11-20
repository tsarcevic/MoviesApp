package com.example.comp.moviesapp.data.response;

import com.example.comp.moviesapp.data.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by COMP on 16.11.2017..
 */

public class MovieResponse {

    private int totalResults;
    private boolean response;
    @SerializedName("Search")
    private List<Movie> moviesList;

    public int getTotalResults() {
        return totalResults;
    }

    public boolean isResponse() {
        return response;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }
}

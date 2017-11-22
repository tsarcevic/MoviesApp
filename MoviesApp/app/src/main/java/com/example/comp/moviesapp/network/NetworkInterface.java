package com.example.comp.moviesapp.network;

import com.example.comp.moviesapp.data.response.MovieResponse;

import retrofit2.Callback;

/**
 * Created by COMP on 16.11.2017..
 */

public interface NetworkInterface {
    void getTopRatedMovies(Callback<MovieResponse> movieListCallback);

    void getMovieById(Callback<MovieResponse> movieCallback, int id);
}
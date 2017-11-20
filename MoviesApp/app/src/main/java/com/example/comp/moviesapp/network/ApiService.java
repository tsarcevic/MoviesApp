package com.example.comp.moviesapp.network;

import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.data.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by COMP on 16.11.2017..
 */

public interface ApiService {

    @GET()
    Call<Movie> getMovieById(@Query("id") String id, @Query("apikey") String apiKey);

    @GET()
    Call<MovieResponse> getQueriedMovies(@Query("s") String movieName, @Query("apikey") String apiKey);
}

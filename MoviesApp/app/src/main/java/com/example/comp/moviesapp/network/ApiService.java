package com.example.comp.moviesapp.network;

import com.example.comp.moviesapp.data.response.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by COMP on 16.11.2017..
 */

public interface ApiService {

    @GET("/movie/{movie_id}")
    Call<MovieResponse> getMovieById(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("/movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}

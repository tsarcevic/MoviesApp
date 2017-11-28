package com.example.comp.moviesapp.network;

import com.example.comp.moviesapp.constants.Constants;
import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.data.response.MovieResponse;

import retrofit2.Callback;

/**
 * Created by COMP on 16.11.2017..
 */

public class NetworkManager implements NetworkInterface {

    private static NetworkManager networkManager;

    private final ApiService apiService;

    public NetworkManager() {
        apiService = BackendFactory.getApiServiceInstance();
    }

    public static NetworkManager getInstance() {
        if (networkManager == null) {
            networkManager = new NetworkManager();
        }

        return networkManager;
    }

    @Override
    public void getTopRatedMovies(Callback<MovieResponse> movieListCallback) {
        apiService.getTopRatedMovies(Constants.API_KEY, 1).enqueue(movieListCallback);
    }

    @Override
    public void getMovieById(Callback<Movie> movieCallback, int id) {
        apiService.getMovieById(id, Constants.API_KEY).enqueue(movieCallback);
    }

    @Override
    public void getMovieByName(Callback<MovieResponse> movieByNameCallback, String movieName) {
        apiService.getMovieByName(Constants.API_KEY, movieName, 1).enqueue(movieByNameCallback);
    }
}

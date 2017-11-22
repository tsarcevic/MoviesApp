package com.example.comp.moviesapp.presentation;

import android.support.annotation.NonNull;

import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.data.response.MovieResponse;
import com.example.comp.moviesapp.database.DatabaseInterface;
import com.example.comp.moviesapp.network.NetworkInterface;
import com.example.comp.moviesapp.ui.all_movies.AllMoviesInterface;
import com.example.comp.moviesapp.utils.StringUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by COMP on 16.11.2017..
 */

public class AllMoviesPresenter implements AllMoviesInterface.Presenter {

    protected AllMoviesInterface.View view;

    private final NetworkInterface networkInterface;
    private final DatabaseInterface databaseInterface;

    private List<Movie> moviesList;

    public AllMoviesPresenter(NetworkInterface service, DatabaseInterface database) {
        this.networkInterface = service;
        this.databaseInterface = database;
    }

    @Override
    public void setView(AllMoviesInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {
        fetchData();
    }

    @Override
    public void checkInput(String text) {
        String helpString = text.trim();
        if (!StringUtils.checkIfStringNotEmpty(helpString)) {
            view.searchedTermError();
        } else {
            view.searchedTermSuccess();
        }
    }

    @Override
    public void fetchData() {
        networkInterface.getTopRatedMovies(getMoviesListCallback());
    }

    @Override
    public void onMovieChosen(int id) {
        networkInterface.getMovieById(getMovieCallback(), id);
    }

    @NonNull
    private Callback<MovieResponse> getMoviesListCallback() {
        return new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response != null && response.body() != null && response.body().getMoviesList().get(0) != null) {
                    moviesList = response.body().getMoviesList();
                    view.showMoviesList(moviesList);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                view.showConnectionError();
            }
        };
    }

    private Callback<MovieResponse> getMovieCallback() {
        return new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response != null && response.body() != null && response.body().getMoviesList().get(0) != null) {
                    Movie movie = response.body().getMoviesList().get(0);
                    addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                view.showConnectionError();
            }
        };
    }

    private void addMovie(Movie movie) {
        databaseInterface.addMovieToDatabase(movie);
        view.movieAdded();
    }

    @Override
    public void itemClicked(int id) {
        view.navigateToMovieInfo(id);
    }

    @Override
    public void itemLongClicked(int id) {
        view.showAddDialog(id);
    }
}

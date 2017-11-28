package com.example.comp.moviesapp.presentation;

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
            view.onSearchedTermError();
        } else {
            view.searchedTermSuccess(helpString);
        }
    }

    @Override
    public void fetchData() {
        networkInterface.getTopRatedMovies(getMoviesListCallback());
    }

    @Override
    public void onSearchedTermSuccess(String movieName) {
        networkInterface.getMovieByName(getMovieByNameCallback(), movieName);
    }

    @Override
    public void onMovieChosen(int id) {
        networkInterface.getMovieById(getMovieCallback(), id);
    }

    protected Callback<MovieResponse> getMovieByNameCallback() {
        return new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response != null && response.body() != null && response.body().getMoviesList() != null) {
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

    protected Callback<MovieResponse> getMoviesListCallback() {
        return new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response != null && response.body() != null && response.body().getMoviesList() != null) {
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

    protected Callback<Movie> getMovieCallback() {
        return new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response != null && response.body() != null) {
                    Movie movie = response.body();
                    addMovie(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                view.showConnectionError();
            }
        };
    }

    private void addMovie(Movie movie) {
        databaseInterface.addMovieToDatabase(movie);
        view.movieAdded();
    }

    @Override
    public void onItemClicked(int id) {
        view.navigateToMovieInfo(id);
    }

    @Override
    public void onItemLongClicked(int id) {
        view.showAddDialog(id);
    }

    @Override
    public void watchlistClicked() {
        view.navigateToWatchlist();
    }
}

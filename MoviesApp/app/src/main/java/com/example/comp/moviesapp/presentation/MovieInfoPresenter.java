package com.example.comp.moviesapp.presentation;

import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.database.DatabaseInterface;
import com.example.comp.moviesapp.database.DatabaseManager;
import com.example.comp.moviesapp.network.NetworkInterface;
import com.example.comp.moviesapp.network.NetworkManager;
import com.example.comp.moviesapp.ui.movie_info.MovieInfoInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by COMP on 16.11.2017..
 */

public class MovieInfoPresenter implements MovieInfoInterface.Presenter {

    protected MovieInfoInterface.View view;

    private final NetworkInterface networkInterface;
    private final DatabaseInterface databaseInterface;

    Movie movie;

    public MovieInfoPresenter(NetworkManager instance, DatabaseManager databaseInstance) {
        this.networkInterface = instance;
        this.databaseInterface = databaseInstance;
    }

    @Override
    public void setView(MovieInfoInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReadyFromAllMovies(int intExtra) {
        if (intExtra != -1) {
            fetchMovieDataFromApi(intExtra);
        } else {
            view.showNoIdError();
        }
    }

    @Override
    public void viewReadyFromWatchlist(int intExtra) {
        if (intExtra != -1) {
            fetchMovieDataFromDatabase(intExtra);
        } else {
            view.showNoIdError();
        }
    }

    private void fetchMovieDataFromDatabase(int intExtra) {
        movie = databaseInterface.getMovieById(intExtra);
        if (movie != null) {
            showMovieInfo(movie);
        } else {
            view.showNoMovieDatabaseError();
        }
    }

    private void fetchMovieDataFromApi(int intExtra) {
        networkInterface.getMovieById(getMovieCallback(), intExtra);
    }

    protected Callback<Movie> getMovieCallback() {
        return new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response != null && response.body() != null) {
                    movie = response.body();
                    showMovieInfo(movie);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                    view.showConnectionError();
            }
        };
    }

    private void showMovieInfo(Movie movie) {
        view.showMoviePicture(movie.getPoster());
        view.showMovieTitle(movie.getTitle());
        view.showMoviePlot(movie.getPlot());
        view.showMovieYear(movie.getYear());
        view.showMovieVote(movie.getVote_average());
    }
}

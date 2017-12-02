package com.example.comp.moviesapp.presentation;

import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.database.DatabaseInterface;
import com.example.comp.moviesapp.database.DatabaseManager;
import com.example.comp.moviesapp.ui.movies_watchlist.WatchListInterface;

import java.util.List;

/**
 * Created by COMP on 16.11.2017..
 */

public class WatchListPresenter implements WatchListInterface.Presenter {

    protected WatchListInterface.View view;

    private final DatabaseInterface databaseInterface;

    private List<Movie> moviesList;

    public WatchListPresenter(DatabaseManager databaseInstance) {
        this.databaseInterface = databaseInstance;
    }

    @Override
    public void setView(WatchListInterface.View view) {
        this.view = view;
    }

    @Override
    public void viewReady() {
        fetchData();
    }

    private void fetchData() {
        moviesList = databaseInterface.fetchData();
        if (moviesList.isEmpty()) {
            view.showNoDataText();
        } else {
            view.showMoviesList(moviesList);
            view.hideNoDataText();
        }
    }

    @Override
    public void onItemClicked(int id) {
        view.navigateToMovieInfo(id);
    }

    @Override
    public void onItemLongClicked(int id) {
        view.showDeleteDialog(id);
    }

    @Override
    public void onDeleteClicked(int id) {
        databaseInterface.deleteMovie(id);

        for (Movie movie : moviesList) {
            if (id == movie.getId()) {
                moviesList.remove(movie);
                view.showMoviesList(moviesList);
                break;
            }
        }
        view.showMovieDeletedInfo();
    }

    @Override
    public void onFilmListClicked() {
        view.navigateToFilmList();
    }
}

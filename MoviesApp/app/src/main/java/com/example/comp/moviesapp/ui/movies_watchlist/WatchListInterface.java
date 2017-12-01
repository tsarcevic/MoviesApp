package com.example.comp.moviesapp.ui.movies_watchlist;

import com.example.comp.moviesapp.data.model.Movie;

import java.util.List;

/**
 * Created by COMP on 16.11.2017..
 */

public interface WatchListInterface {

    interface View {

        void showMoviesList(List<Movie> moviesList);

        void navigateToMovieInfo(int id);

        void showDeleteDialog(int id);

        void showNoData();

        void showMovieDeletedInfo();

        void navigateToFilmList();
    }

    interface Presenter {

        void setView(View view);

        void viewReady();

        void onItemClicked(int id);

        void onItemLongClicked(int id);

        void onDeleteClicked(int id);

        void onFilmListClicked();
    }
}

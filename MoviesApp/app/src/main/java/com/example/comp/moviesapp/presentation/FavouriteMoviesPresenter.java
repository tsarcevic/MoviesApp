package com.example.comp.moviesapp.presentation;

import com.example.comp.moviesapp.ui.favourite_movies.FavouriteMoviesInterface;

/**
 * Created by COMP on 16.11.2017..
 */

public class FavouriteMoviesPresenter implements FavouriteMoviesInterface.Presenter {

    FavouriteMoviesInterface.View view;

    @Override
    public void setView(FavouriteMoviesInterface.View view) {
        this.view = view;
    }
}

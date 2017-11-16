package com.example.comp.moviesapp.presentation;

import com.example.comp.moviesapp.ui.all_movies.AllMoviesInterface;

/**
 * Created by COMP on 16.11.2017..
 */

public class AllMoviesPresenter implements AllMoviesInterface.Presenter {

    AllMoviesInterface.View view;

    @Override
    public void setView(AllMoviesInterface.View view) {
        this.view = view;
    }
}

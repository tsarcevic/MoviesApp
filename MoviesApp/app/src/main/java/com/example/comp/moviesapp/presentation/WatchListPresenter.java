package com.example.comp.moviesapp.presentation;

import com.example.comp.moviesapp.ui.favourite_movies.WatchListInterface;

/**
 * Created by COMP on 16.11.2017..
 */

public class WatchListPresenter implements WatchListInterface.Presenter {

    WatchListInterface.View view;

    @Override
    public void setView(WatchListInterface.View view) {
        this.view = view;
    }
}

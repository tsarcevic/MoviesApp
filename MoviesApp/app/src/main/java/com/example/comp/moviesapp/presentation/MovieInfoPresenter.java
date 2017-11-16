package com.example.comp.moviesapp.presentation;

import com.example.comp.moviesapp.ui.movie_info.MovieInfoInterface;

/**
 * Created by COMP on 16.11.2017..
 */

public class MovieInfoPresenter implements MovieInfoInterface.Presenter {

    MovieInfoInterface.View view;

    @Override
    public void setView(MovieInfoInterface.View view) {
        this.view = view;
    }
}

package com.example.comp.moviesapp.ui.all_movies;

import com.example.comp.moviesapp.data.model.Movie;

import java.util.List;

/**
 * Created by COMP on 16.11.2017..
 */

public interface AllMoviesInterface {

    interface View {

        void showMoviesList(List<Movie> moviesList);

        void searchedTermError();

        void searchedTermSuccess();

        void showConnectionError();

        void navigateToMovieInfo(int id);

        void movieAdded();

        void showAddDialog(int id);
    }

    interface Presenter {

        void setView (View view);

        void checkInput(String text);

        void fetchData();

        void itemClicked(int id);

        void itemLongClicked(int id);

        void onMovieChosen(int id);

        void viewReady();
    }
}

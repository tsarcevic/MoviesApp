package com.example.comp.moviesapp.ui.movie_info;

/**
 * Created by COMP on 16.11.2017..
 */

public interface MovieInfoInterface {

    interface View {

        void showNoIdError();

        void showNoMovieDatabaseError();

        void showMoviePicture(String poster);

        void showMovieTitle(String title);

        void showMoviePlot(String plot);

        void showMovieYear(String year);

        void showMovieVote(double vote_average);

        void showConnectionError();

        void navigateBack();
    }

    interface Presenter {

        void setView(View view);

        void viewReadyFromAllMovies(int intExtra);

        void viewReadyFromWatchlist(int intExtra);

        void onBackClicked();
    }
}

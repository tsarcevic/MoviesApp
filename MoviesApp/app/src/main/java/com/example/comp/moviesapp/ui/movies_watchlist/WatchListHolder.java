package com.example.comp.moviesapp.ui.movies_watchlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.interfaces.MovieClickListener;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by COMP on 20.11.2017..
 */

class WatchListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.root)
    ViewGroup rootView;

    @BindView(R.id.movie_image)
    ImageView movieImage;

    @BindView(R.id.movie_name)
    TextView movieName;

    @BindView(R.id.movie_rating)
    TextView movieRating;

    @BindView(R.id.movie_year)
    TextView movieYear;

    @BindView(R.id.movie_plot)
    TextView moviePlot;

    @BindView(R.id.movie_watched)
    TextView movieWatched;

    @BindColor(R.color.white)
    int white;

    @BindColor(R.color.gray)
    int gray;

    @BindColor(R.color.green)
    int green;

    @BindColor(R.color.red)
    int red;

    private String id;

    MovieClickListener movieClickListener;

    public WatchListHolder(View itemView, MovieClickListener movieClickListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        this.movieClickListener = movieClickListener;
    }

    public void setMovieInfo(Movie movie) {
        if (movie != null) {
            id = movie.getId();

            movieName.setText(movie.getTitle());
            moviePlot.setText(movie.getPlot());
            movieRating.setText(movie.getImbdRating());
            movieYear.setText(movie.getYear());

            if (movie.isWatched()) {
                movieWatched.setBackgroundColor(green);
            } else {
                movieWatched.setBackgroundColor(red);
            }
        }
    }

    @OnClick
    public void movieClicked() {
        if (movieClickListener != null) {
            movieClickListener.onItemClicked(id);
        }
    }

    @OnLongClick
    public void movieLongClicked() {
        if (movieClickListener != null) {
            movieClickListener.onItemLongClicked(id);
        }
    }

    public void setGrayBackground() {
        rootView.setBackgroundColor(gray);
    }

    public void setWhiteBackground() {
        rootView.setBackgroundColor(white);
    }
}
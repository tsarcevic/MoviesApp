package com.example.comp.moviesapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.constants.Constants;
import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.interfaces.MovieClickListener;
import com.example.comp.moviesapp.utils.ImageUtils;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by COMP on 20.11.2017..
 */

class MoviesHolder extends RecyclerView.ViewHolder {

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

    @BindColor(R.color.white)
    int white;

    @BindColor(R.color.gray)
    int gray;

    private int id;

    MovieClickListener movieClickListener;

    public MoviesHolder(View itemView, MovieClickListener movieClickListener) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        this.movieClickListener = movieClickListener;
    }

    public void setMovieInfo(Movie movie) {
        if (movie != null) {

            id = movie.getId();
            // TODO: 27.11.2017. skontaj kak ide url slike
            String imageURL = Constants.BASE_URL_PATH + movie.getPoster();
            String imageURL2 = "https://searchengineland.com/figz/wp-content/seloads/2015/08/movie-film-video-production-ss-1920-800x450.jpg";
            ImageUtils.setPicture(movieImage, imageURL);
            movieName.setText(movie.getTitle());
            moviePlot.setText(movie.getPlot());
            movieRating.setText(String.valueOf(movie.getVote_average()));
            movieYear.setText(movie.getYear());
        }
    }

    @OnClick
    public void movieClicked() {
        if (movieClickListener != null) {
            movieClickListener.onItemClicked(id);
        }
    }

    @OnLongClick
    public boolean movieLongClicked() {
        if (movieClickListener != null) {
            movieClickListener.onItemLongClicked(id);

            return true;
        } else {
            return false;
        }
    }

    public void setGrayBackground() {
        rootView.setBackgroundColor(gray);
    }

    public void setWhiteBackground() {
        rootView.setBackgroundColor(white);
    }
}

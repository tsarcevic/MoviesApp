package com.example.comp.moviesapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comp.moviesapp.R;
import com.example.comp.moviesapp.data.model.Movie;
import com.example.comp.moviesapp.interfaces.MovieClickListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by COMP on 20.11.2017..
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesHolder> implements Serializable {

    private List<Movie> moviesList = new ArrayList<>();

    MovieClickListener movieClickListener;

    public void setMovieClickListener(MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList.clear();
        this.moviesList.addAll(moviesList);
        notifyDataSetChanged();
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View movieView = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MoviesHolder(movieView, movieClickListener);
    }

    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {
        Movie movie = moviesList.get(position);

        holder.setMovieInfo(movie);
        if(position % 2 == 0) {
            holder.setGrayBackground();
        } else {
            holder.setWhiteBackground();
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

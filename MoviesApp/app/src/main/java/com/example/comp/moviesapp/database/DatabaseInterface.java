package com.example.comp.moviesapp.database;

import com.example.comp.moviesapp.data.model.Movie;

import java.util.List;

/**
 * Created by COMP on 21.11.2017..
 */

public interface DatabaseInterface {
    void addMovieToDatabase(Movie movie);

    List<Movie> fetchData();

    void deleteMovie(int id);

    Movie getMovieById(int intExtra);
}

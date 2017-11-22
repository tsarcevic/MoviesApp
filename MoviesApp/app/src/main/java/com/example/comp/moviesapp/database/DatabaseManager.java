package com.example.comp.moviesapp.database;

import com.example.comp.moviesapp.data.model.Movie;

/**
 * Created by COMP on 21.11.2017..
 */

public class DatabaseManager implements DatabaseInterface {

    private static DatabaseManager databaseManager;
    private static DatabaseHelper databaseHelper;

    public DatabaseManager() {
        databaseHelper = DatabaseHelper.getDatabaseInstance();
    }

    public static DatabaseManager getDatabaseInstance() {
        if (databaseManager == null) {
            databaseManager = new DatabaseManager();
        }

        return databaseManager;
    }

    @Override
    public void addMovieToDatabase(Movie movie) {
        if (movie != null) {
            databaseHelper.addMovie(movie);
        }
    }
}

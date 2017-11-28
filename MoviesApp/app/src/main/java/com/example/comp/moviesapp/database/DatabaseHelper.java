package com.example.comp.moviesapp.database;

import com.example.comp.moviesapp.data.model.Movie;

import java.util.List;

import io.realm.Realm;

/**
 * Created by COMP on 16.11.2017..
 */

public class DatabaseHelper {

    private static DatabaseHelper databaseInstance;

    private Realm realm;

    private DatabaseHelper(Realm realm) {
        this.realm = realm;
    }

    public static DatabaseHelper getDatabaseInstance() {
        if (databaseInstance == null) {
            databaseInstance = new DatabaseHelper(Realm.getDefaultInstance());
        }

        return databaseInstance;
    }

    public List<Movie> getAllMovies() {
        return realm.copyFromRealm(realm.where(Movie.class).findAll());
    }

    public void addMovie(Movie movie) {
        if (movie != null) {
            realm.beginTransaction();

            if (realm.copyFromRealm(realm.where(Movie.class).equalTo("id", movie.getId()).findFirst()) == null) {
                realm.copyToRealm(movie);
            }

            realm.commitTransaction();
        }
    }

    public void removeMovie(int id) {
        if (id != 0) {
            realm.beginTransaction();

            Movie movieToDelete = realm.copyFromRealm(realm.where(Movie.class).equalTo("id", id).findFirst());

            if (movieToDelete != null) {
                movieToDelete.deleteFromRealm();
            }

            realm.commitTransaction();
        }
    }

    public Movie findMovie(int id) {
        Movie queriedMovie = new Movie();

        if (id != 0) {
            realm.beginTransaction();

            queriedMovie = realm.copyFromRealm(realm.where(Movie.class).equalTo("id", id).findFirst());

            realm.commitTransaction();
        }

        return queriedMovie;

    }
}

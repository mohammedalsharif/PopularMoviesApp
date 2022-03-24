package com.examples.popularmoviesapp.data.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.examples.popularmoviesapp.model.Movie;

import java.util.List;

public class Repository {

    MovieDAO movieDAO;

    public Repository(Application application) {
        MovieRoomDatabase database = MovieRoomDatabase.getINSTANCE(application);
        movieDAO = database.movieDAO();
    }

    public void insertMovie(Movie movie) {
        MovieRoomDatabase.EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                movieDAO.insertMovie(movie);
            }
        });

    }

   public void deleteMovie(Movie movie) {
        MovieRoomDatabase.EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                movieDAO.deleteMovie(movie);
            }
        });
    }

    public LiveData<List<Movie>> getAllFavoriteMovies() {
        return movieDAO.getAllFavoriteMovies();
    }


    public void setFavoriteMovie(int movieId) {
        MovieRoomDatabase.EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                movieDAO.setFavoriteMovie(movieId);
            }
        });
    }


    public void UnFavoriteMovie(int movieId) {
        MovieRoomDatabase.EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                movieDAO.UnFavoriteMovie(movieId);
            }
        });

    }

    public LiveData<Integer> IsFavorite(int movieId) {
       return movieDAO.IsFavorite(movieId);
    }

}

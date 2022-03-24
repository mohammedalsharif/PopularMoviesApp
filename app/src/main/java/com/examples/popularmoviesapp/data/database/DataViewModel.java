package com.examples.popularmoviesapp.data.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.examples.popularmoviesapp.model.Movie;

import java.util.List;

public class DataViewModel extends AndroidViewModel {
    public static Repository repository;

    public DataViewModel(@NonNull Application application) {
        super(application);
        repository =new Repository(application);

    }

    public void insertMovie(Movie movie) {
        repository.insertMovie(movie);
    }

    public LiveData<List<Movie>> getAllFavoriteMovies() {
        return repository.getAllFavoriteMovies();
    }


    public void setFavoriteMovie(int movieId) {
        repository.setFavoriteMovie(movieId);
    }


    public void UnFavoriteMovie(int movieId) {
        repository.UnFavoriteMovie(movieId);
    }

    public LiveData<Integer> IsFavorite(int movieId) {
        return repository.IsFavorite(movieId);
    }

    public void deleteMovie(Movie movie) {
        repository.deleteMovie(movie);
    }


}

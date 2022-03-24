package com.examples.popularmoviesapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.examples.popularmoviesapp.model.Movie;

import java.util.List;

@Dao
@TypeConverters(Converters.class)
public interface MovieDAO {

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("SELECT * From Movie where is_favorite = 1")
    LiveData<List<Movie>> getAllFavoriteMovies();

    @Query("UPDATE Movie SET is_favorite = 1 WHERE id = :movieId")
    void setFavoriteMovie(int movieId);

   @Query("UPDATE Movie SET is_favorite = 0 WHERE id =:movieId")
    void UnFavoriteMovie(int movieId);

   @Query("SELECT is_favorite From movie WHERE id =:movieId")
   LiveData<Integer> IsFavorite(int movieId);
}

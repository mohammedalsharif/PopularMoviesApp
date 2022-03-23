package com.examples.popularmoviesapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.examples.popularmoviesapp.model.Movie;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Movie.class},version = 1,exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    private static final String NAME_DB = "Movie_Database";

    public abstract MovieDAO movieDAO();

    public static volatile MovieRoomDatabase INSTANCE;

    private static final int NUMBER_THREADS = 4;
    static final ExecutorService EXECUTOR_SERVICE= Executors.newFixedThreadPool(NUMBER_THREADS);

    public static MovieRoomDatabase getINSTANCE(final Context context) {
        if (INSTANCE==null){
            synchronized (MovieRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE=Room.databaseBuilder(context.getApplicationContext()
                            ,MovieRoomDatabase.class,NAME_DB).build();
                }
            }
        }
        return INSTANCE;
    }
}

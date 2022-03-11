package com.examples.popularmoviesapp.data.api;

import com.examples.popularmoviesapp.model.Movie;
import com.examples.popularmoviesapp.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataInterface {
    @GET("popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("now_playing")
    Call<MovieResponse> getNowPlaying(@Query("api_key") String apiKey);

}

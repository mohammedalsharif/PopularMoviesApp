package com.examples.popularmoviesapp.data.api;

import com.examples.popularmoviesapp.model.Movie;
import com.examples.popularmoviesapp.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataInterface {
    @GET("movie/popular")
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieResponse> getSearchMovieList( @Query("api_key") String apiKey
            ,@Query("query") String query ,
             @Query("page") String page);

    @GET("movie/{movie_id}?")
    Call<MovieResponse> getSearchById(@Path("movie_id") int movieId);

}

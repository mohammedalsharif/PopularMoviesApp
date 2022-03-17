package com.examples.popularmoviesapp.data.api;

import com.examples.popularmoviesapp.model.MovieResponse;
import com.examples.popularmoviesapp.model.ReviewsResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ApiClint {
    // private static final String baseUrl = "https://api.themoviedb.org/3/movie/";
    private static final String baseUrl = "https://api.themoviedb.org/3/";
    private GetDataInterface dataInterface;
    private static ApiClint INSTANCE;
    public ApiClint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        dataInterface = retrofit.create(GetDataInterface.class);
    }

    public static ApiClint getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ApiClint();
        }
        return INSTANCE;
    }

    public Call<MovieResponse> getPopularMovies(String apiKey) {
        return dataInterface.getPopularMovies(apiKey);
    }

    public Call<MovieResponse> getTopRatedMovies(String apiKey) {
        return dataInterface.getTopRatedMovies(apiKey);
    }


    public Call<MovieResponse> getNowPlaying(String apiKey) {
        return dataInterface.getNowPlaying(apiKey);
    }

    public Call<MovieResponse> getSearchMovieList(String apiKey, String query, int page) {
        return dataInterface.getSearchMovieList(apiKey, query, page);
    }

   public Call<ReviewsResponse> getReviews( int id,String apiKye) {
        return dataInterface.getReviews(id,apiKye);
    }


}

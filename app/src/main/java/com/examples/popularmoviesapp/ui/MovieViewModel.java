package com.examples.popularmoviesapp.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.examples.popularmoviesapp.data.api.ApiClint;
import com.examples.popularmoviesapp.model.Movie;
import com.examples.popularmoviesapp.model.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    MutableLiveData<MovieResponse> mutableLiveData = new MutableLiveData<>();
    ApiClint apiClint = ApiClint.getINSTANCE();
    public static final String apiKey = "ecf90d6c2b60085eb2de8f0495ebffbb";


    public void getPopularMovies() {
         apiClint.getPopularMovies(apiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

                Log.e("MovieViewModel", t.getMessage());
            }
        });
    }
    public void getTopRatedMovies(){
        apiClint.getTopRatedMovies(apiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
    public void getNowPlayingMovies(){
        apiClint.getNowPlaying(apiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

}

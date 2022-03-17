package com.examples.popularmoviesapp.viewmodels;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.examples.popularmoviesapp.Utils.AppExecutor;
import com.examples.popularmoviesapp.data.api.ApiClint;
import com.examples.popularmoviesapp.model.Movie;
import com.examples.popularmoviesapp.model.MovieResponse;
import com.examples.popularmoviesapp.model.ReviewsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    public MutableLiveData<ReviewsResponse> mDataReview = new MutableLiveData<>();
    public MutableLiveData<MovieResponse> mutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Movie>> mutableLiveMovieData = new MutableLiveData<>();

    ApiClint apiClint = ApiClint.getINSTANCE();
    public static final String apiKey = "ecf90d6c2b60085eb2de8f0495ebffbb";
    public RetrieveMoviesRunnable retrieveMoviesRunnable;
    private String mQuery;
    private int mPageNumber;

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

    public void getTopRatedMovies() {
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

    public void getNowPlayingMovies() {
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


    public void getSearchMovieFromApi(String query, int pageNumber) {

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);
        Future myHandler = AppExecutor.getINSTANCE().getExecutorService().submit(retrieveMoviesRunnable);

        AppExecutor.getINSTANCE().getExecutorService().schedule(new Runnable() {
            @Override
            public void run() {

                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);


    }

    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {

            try {
                Response<MovieResponse> response = apiClint.getSearchMovieList(apiKey, query, pageNumber).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieResponse) response.body()).getMovieList());
                    if (pageNumber == 1) {
                        mutableLiveMovieData.postValue(list);
                    } else {
                        List<Movie> currentMovies = mutableLiveMovieData.getValue();
                        currentMovies.addAll(list);
                        mutableLiveMovieData.postValue(currentMovies);
                    }

                } else {
                    mutableLiveData.postValue(null);
                    Log.e("TAG", "run: " + response.errorBody());
                }

            } catch (IOException e) {
                e.printStackTrace();
                mutableLiveData.postValue(null);
            }

        }


        private void cancelRunnable() {
            Log.d("TAG", "cancel Search Runnable ");
            cancelRequest = true;
        }
    }


    public void getSearchMovie(String query, int pageNumber) {
        this.mPageNumber = pageNumber;
        this.mQuery = query;
        getSearchMovieFromApi(query, pageNumber);

    }

    public void searchNextPage() {
        getSearchMovie(mQuery, mPageNumber + 1);
    }

    public void getReviews(int id) {
         apiClint.getReviews(id,apiKey).enqueue(new Callback<ReviewsResponse>() {
             @Override
             public void onResponse(Call<ReviewsResponse> call, Response<ReviewsResponse> response) {
                 mDataReview.setValue(response.body());
             }

             @Override
             public void onFailure(Call<ReviewsResponse> call, Throwable t) {
                 Log.e("TAG", "onFailure: "+t.getMessage() );
             }
         });
    }

}

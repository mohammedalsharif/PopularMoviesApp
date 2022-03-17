package com.examples.popularmoviesapp.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.examples.popularmoviesapp.adapters.ReviewsAdapter;
import com.examples.popularmoviesapp.databinding.ActivityMovieDetailsBinding;
import com.examples.popularmoviesapp.model.Movie;
import com.examples.popularmoviesapp.model.Reviews;
import com.examples.popularmoviesapp.model.ReviewsResponse;
import com.examples.popularmoviesapp.viewmodels.MovieViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding binding;
    ActivityResultLauncher<Intent> launcher;

    MovieViewModel mModel;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mModel = new ViewModelProvider(this).get(MovieViewModel.class);


        Intent result = getIntent();
        if (result != null) {
            movie = (Movie) result.getSerializableExtra("movieItem");

            Picasso.get().load(movie.getPosterPath()).fit().into(binding.movieDetailsInfo.imagePoster);
            Picasso.get().load(movie.getBackdropPath()).fit().into(binding.imageMovieBackdrop);
            binding.movieDetailsInfo.textTitle.setText(movie.getTitle());
            binding.movieDetailsInfo.textVote.setText(String.valueOf(movie.getVoteAverage()));
            binding.movieDetailsInfo.textLanguage.setText(movie.getOriginalLanguage());
            binding.movieDetailsInfo.textReleaseDate.setText(movie.getReleaseDate());
            binding.movieDetailsInfo.textOverview.setText(movie.getOverview());
            handleRecReviews();
            // binding.movieDetailsInfo.chipGroup.addView(movie.getGenreIds());
        }


    }

    private void handleRecReviews() {
        ReviewsAdapter adapter = new ReviewsAdapter(new ArrayList<>());
        mModel.getReviews(movie.getId());
        mModel.mDataReview.observe(this, new Observer<ReviewsResponse>() {
            @Override
            public void onChanged(ReviewsResponse reviewsResponse) {

               adapter.setReviewsList(reviewsResponse.getResults());

            }
        });

        binding.movieDetailsInfo.listReviews.setLayoutManager(new LinearLayoutManager(this));
        binding.movieDetailsInfo.listReviews.setAdapter(adapter);
        binding.movieDetailsInfo.listReviews.setHasFixedSize(true);
    }

}
package com.examples.popularmoviesapp.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.Utils.ActivityUtils;
import com.examples.popularmoviesapp.adapters.CastAdapter;
import com.examples.popularmoviesapp.adapters.ReviewsAdapter;
import com.examples.popularmoviesapp.adapters.TrailerAdapter;
import com.examples.popularmoviesapp.data.database.DataViewModel;
import com.examples.popularmoviesapp.databinding.ActivityMovieDetailsBinding;
import com.examples.popularmoviesapp.model.CreditsResponse;
import com.examples.popularmoviesapp.model.Genre;
import com.examples.popularmoviesapp.model.Movie;
import com.examples.popularmoviesapp.model.MovieResponse;
import com.examples.popularmoviesapp.model.Reviews;
import com.examples.popularmoviesapp.model.ReviewsResponse;
import com.examples.popularmoviesapp.model.VideosResponse;
import com.examples.popularmoviesapp.viewmodels.MovieViewModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.chip.Chip;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {
    ActivityMovieDetailsBinding binding;
    MovieViewModel mModel;
    DataViewModel DViewModel;
    Movie movie;
int fav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        mModel = new ViewModelProvider(this).get(MovieViewModel.class);

        Intent result = getIntent();

        if (result != null) {
            movie = (Movie) result.getSerializableExtra("movieItem");
            Picasso.get().load(movie.getPosterPath()).fit().into(binding.movieDetailsInfo.imagePoster);
            setBitmapImageInMovie();
            Picasso.get().load(movie.getBackdropPath()).fit().into(binding.imageMovieBackdrop);
            binding.movieDetailsInfo.textTitle.setText(movie.getTitle());
            binding.movieDetailsInfo.textVote.setText(String.valueOf(movie.getVoteAverage()));
            binding.movieDetailsInfo.textLanguage.setText(movie.getOriginalLanguage());
            binding.movieDetailsInfo.textReleaseDate.setText(movie.getReleaseDate());
            binding.movieDetailsInfo.textOverview.setText(movie.getOverview());
            mModel.getSearchById(movie.getId());
            mModel.mutableLiveData.observe(this, new Observer<MovieResponse>() {
                @Override
                public void onChanged(MovieResponse movieResponse) {
                    addChip(movieResponse.getGenreList());

                }
            });
            handleRecCasts();
            handleRecTrailer();
            handleRecReviews();
            setupToolbar();

        }


    }

    private void setBitmapImageInMovie() {
        Picasso.get().load(movie.getPosterPath()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                movie.setImageByte(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        MenuItem item = menu.findItem(R.id.action_fav_details);
        DViewModel.IsFavorite(movie.getId()).observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer!=null){
                    fav=integer;
                if (integer==1) {
                    item.setIcon(R.drawable.ic_favorite_black_24dp).setTitle("fav");
                }else {
                    item.setIcon(R.drawable.ic_favorite_border_black_24dp).setTitle("unFav");
                }
            }}
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fav_details:
                if (fav==0){

                    DViewModel.insertMovie(movie);
                    DViewModel.setFavoriteMovie(movie.getId());
                    fav=1;
                }else{
                    DViewModel.UnFavoriteMovie(movie.getId());
                    DViewModel.deleteMovie(movie);
                    fav=0;
                }
                invalidateOptionsMenu();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            handleToolbarTitle();
        }

    }


    private void handleToolbarTitle() {
        binding.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                // verify if the toolbar is completely collapsed and set the movie name as the title
                if (scrollRange + verticalOffset == 0) {
                    binding.collapsingToolbar.setTitle(movie.getTitle());

                    isShow = true;
                } else if (isShow) {
                    // display an empty string when toolbar is expanded
                    binding.collapsingToolbar.setTitle(" ");
                    isShow = false;
                }

            }
        });
    }

    private void handleRecTrailer() {
        TrailerAdapter adapter = new TrailerAdapter(this);
        mModel.getVideos(movie.getId());
        mModel.mDataVideos.observe(this, new Observer<VideosResponse>() {
            @Override
            public void onChanged(VideosResponse videosResponse) {
                adapter.setTrailerList(videosResponse.getTrailerList());
            }
        });
        binding.movieDetailsInfo.listTrailers.setHasFixedSize(true);
        binding.movieDetailsInfo.listTrailers.setLayoutManager
                (new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.movieDetailsInfo.listTrailers.setAdapter(adapter);
    }

    private void addChip(List<Genre> genreList) {
        for (Genre genre : genreList) {
            Chip chip = new Chip(MovieDetailsActivity.this);
            chip.setText(genre.getName());
            binding.movieDetailsInfo.chipGroup.addView(chip);
        }
    }

    private void handleRecCasts() {
        CastAdapter adapter = new CastAdapter();
        binding.movieDetailsInfo.listCast.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mModel.getCredits(movie.getId());
        mModel.mDataCredits.observe(this, new Observer<CreditsResponse>() {
            @Override
            public void onChanged(CreditsResponse creditsResponse) {
                adapter.setCastList(creditsResponse.getCastList());
            }
        });
        binding.movieDetailsInfo.listCast.setHasFixedSize(true);
        binding.movieDetailsInfo.listCast.setAdapter(adapter);
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
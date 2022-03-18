package com.examples.popularmoviesapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.adapters.MoviesAdapter;
import com.examples.popularmoviesapp.adapters.MoviesListener;
import com.examples.popularmoviesapp.databinding.FragmentDiscoverBinding;
import com.examples.popularmoviesapp.model.Movie;
import com.examples.popularmoviesapp.model.MovieResponse;
import com.examples.popularmoviesapp.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;


public class DiscoverFragment extends Fragment implements MoviesListener {
    MovieViewModel viewModel;
    FragmentDiscoverBinding binding;

    MoviesAdapter adapter = new MoviesAdapter(new ArrayList<>(), DiscoverFragment.this);

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDiscoverBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        PopularMovie();
        handleRecMovies();
        return binding.getRoot();
    }

    private void handleRecMovies() {
        binding.recyclerViewDiscover.setAdapter(adapter);
        binding.recyclerViewDiscover.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerViewDiscover.setHasFixedSize(true);
        if (adapter.getItemCount()==0){
            binding.spinKit.setVisibility(View.VISIBLE);
        }else {
            binding.spinKit.setVisibility(View.GONE);
        }
        binding.recyclerViewDiscover.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.searchNextPage();
                }
            }
        });
    }

    private void PopularMovie() {
        viewModel.getPopularMovies();
        viewModel.mutableLiveData.observe(getActivity(), new Observer<MovieResponse>() {
            @Override
            public void onChanged(MovieResponse movieResponse) {
                if (movieResponse != null) {
                    adapter.setMovies(movieResponse.getMovieList());
                    binding.spinKit.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_item).getActionView();
        searchView.setQueryHint("Looking for movie?");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!s.isEmpty()) {
                    searchMovie(s);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!s.isEmpty()) {
                    searchMovie(s);
                }
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void searchMovie(String s) {
        viewModel.getSearchMovie(s, 1);
        viewModel.mutableLiveMovieData.observe(getActivity(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setMovies(movies);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popular_action:
                PopularMovie();
                return true;
            case R.id.top_rated_action:
                viewModel.getTopRatedMovies();
                return true;
            case R.id.now_playing_action:
                viewModel.getNowPlayingMovies();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnClickItemRec(int position) {
       Movie movie = adapter.getItem(position);
       Intent intent =new Intent(getActivity(), MovieDetailsActivity.class);
       intent.putExtra("movieItem",movie);
        startActivity(intent);

    }
}
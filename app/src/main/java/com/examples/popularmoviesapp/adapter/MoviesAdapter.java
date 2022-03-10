package com.examples.popularmoviesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.databinding.CustomeItemRecMoviesBinding;
import com.examples.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.HolderMovies> {
    List<Movie> movies = new ArrayList<>();

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public HolderMovies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomeItemRecMoviesBinding binding;
        binding = CustomeItemRecMoviesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HolderMovies(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMovies holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.imMovie.setImageResource(movie.getImage());
        holder.binding.tvNameMovieItem.setText(movie.getMovieName());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class HolderMovies extends RecyclerView.ViewHolder {
        CustomeItemRecMoviesBinding binding;

        public HolderMovies(@NonNull CustomeItemRecMoviesBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

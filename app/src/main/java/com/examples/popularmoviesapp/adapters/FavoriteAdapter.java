package com.examples.popularmoviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.databinding.CustomeItemRecFavoritesBinding;
import com.examples.popularmoviesapp.model.FavoriteMovie;
import com.examples.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavViewHolder> {

    List<Movie> favoriteMovieList = new ArrayList<>();

    public FavoriteAdapter(List<Movie> favoriteMovieList) {
        this.favoriteMovieList = favoriteMovieList;
    }

    public void setFavoriteMovieList(List<Movie> favoriteMovieList) {
        this.favoriteMovieList = favoriteMovieList;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomeItemRecFavoritesBinding binding = CustomeItemRecFavoritesBinding.inflate(
                LayoutInflater.from(parent.getContext()
                ), parent, false);
        return new FavViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Movie favoriteMovie = favoriteMovieList.get(position);
        holder.binding.tvNameMovieItem.setText(favoriteMovie.getTitle());
    }

    @Override
    public int getItemCount() {
        return favoriteMovieList.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        CustomeItemRecFavoritesBinding binding;

        public FavViewHolder(@NonNull CustomeItemRecFavoritesBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

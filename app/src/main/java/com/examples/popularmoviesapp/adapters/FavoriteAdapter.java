package com.examples.popularmoviesapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.databinding.CustomeItemRecFavoritesBinding;
import com.examples.popularmoviesapp.model.FavoriteMovie;
import com.examples.popularmoviesapp.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavViewHolder> {

    List<Movie> favoriteMovieList = new ArrayList<>();

    public void setFavoriteMovieList(List<Movie> favoriteMovieList) {
        this.favoriteMovieList = favoriteMovieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomeItemRecFavoritesBinding binding = CustomeItemRecFavoritesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Movie favoriteMovie = favoriteMovieList.get(position);
        Log.e("TAG", "onBindViewHolder: " + favoriteMovie.getTitle());
        holder.binding.tvNameMovieItem.setText(favoriteMovie.getTitle());
        holder.binding.imMovie.setImageBitmap(favoriteMovie.getImageByte());
    }

    @Override
    public int getItemCount() {
        return favoriteMovieList == null ? 0 : favoriteMovieList.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        CustomeItemRecFavoritesBinding binding;

        public FavViewHolder(@NonNull CustomeItemRecFavoritesBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

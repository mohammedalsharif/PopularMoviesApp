package com.examples.popularmoviesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.databinding.CustomItemSearchRecBinding;
import com.examples.popularmoviesapp.databinding.CustomeItemRecMoviesBinding;
import com.examples.popularmoviesapp.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.HolderMovies> {
    List<Movie> movies = new ArrayList<>();
    MoviesListener listener;
    //Context context;

    public MoviesAdapter(List<Movie> movies,MoviesListener listener) {
        this.movies = movies;
        this.listener=listener;
      //  this.context=context;

    }
    public void setMovies(List<Movie> movieArrayList){
        this.movies = movieArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderMovies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomItemSearchRecBinding binding =   CustomItemSearchRecBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HolderMovies(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMovies holder, int position) {
        Movie movie = movies.get(position);
      //  holder.binding.imMovie.setImageResource(movie.getImage());
       holder.binding.tvNameMovieItem.setText(movie.getTitle());
       holder.binding.tvReleaseDate.setText(movie.getReleaseDate());
       holder.binding.tvPop.setText(String.valueOf(movie.getVoteAverage()));
       holder.binding.progressBar.setProgress((int)(movie.getVoteAverage()*10));
       //holder.binding.tvOverview.setText(movie.getOverview());
        holder.binding.ratingBar.setRating((float) (movie.getVoteAverage()/2));
        Picasso.get().load(movie.getPosterPath()).fit()
                .into(holder.binding.imMovie, new Callback() {
                    @Override
                    public void onSuccess() {


                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
        listener.OnClickItemRec(position);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class HolderMovies extends RecyclerView.ViewHolder {
        CustomItemSearchRecBinding binding;

        public HolderMovies(@NonNull   CustomItemSearchRecBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

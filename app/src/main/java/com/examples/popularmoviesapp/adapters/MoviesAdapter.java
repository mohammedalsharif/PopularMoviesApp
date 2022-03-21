package com.examples.popularmoviesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.databinding.CustomItemSearchRecBinding;
import com.examples.popularmoviesapp.model.Movie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.HolderMovies> {
    List<Movie> movies = new ArrayList<>();
    MoviesListener listener;
   Context context;

    public MoviesAdapter(List<Movie> movies, MoviesListener listener) {
        this.movies = movies;
        this.listener = listener;
        //  this.context=context;

    }

    public void setMovies(List<Movie> movieArrayList) {
        this.movies = movieArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HolderMovies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        CustomItemSearchRecBinding binding = CustomItemSearchRecBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new HolderMovies(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMovies holder, int position) {
        Movie movie = movies.get(position);
        holder.binding.tvNameMovieItem.setText(movie.getTitle());
        holder.binding.tvReleaseDate.setText(movie.getReleaseDate());

        holder.binding.tvLanguage.setText(context.getString(R.string.language2) +movie.getOriginalLanguage());
        holder.binding.tvPop.setText(String.valueOf(movie.getVoteAverage()));
        holder.binding.progressBar.setProgress((int) (movie.getVoteAverage() * 10));

        holder.binding.ratingBar.setRating((float) (movie.getVoteAverage() / 2));
        Picasso.get().load(movie.getPosterPath()).fit()
                .into(holder.binding.imMovie, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.binding.spinKitItem.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        holder.binding.spinKitItem.setVisibility(View.VISIBLE);
                    }
                });


    }

    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        } else {
            return movies.size();
        }

    }

    public Movie getItem(int position) {
        return movies.get(position);

    }


    public class HolderMovies extends RecyclerView.ViewHolder {
        CustomItemSearchRecBinding binding;

        public HolderMovies(@NonNull CustomItemSearchRecBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            itemView.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.OnClickItemRec(getAdapterPosition());
                }
            });
        }

    }
}

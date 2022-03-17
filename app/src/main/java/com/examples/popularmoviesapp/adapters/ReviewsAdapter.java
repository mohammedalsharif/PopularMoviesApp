package com.examples.popularmoviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.databinding.CustomeItemRecReviewBinding;
import com.examples.popularmoviesapp.model.Reviews;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.Viewholdear> {

    List<Reviews> reviewsList;

    public ReviewsAdapter(List<Reviews> reviews){
        reviewsList=reviews;
    }

    public void setReviewsList(List<Reviews>reviews){
        this.reviewsList=reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholdear onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomeItemRecReviewBinding binding = CustomeItemRecReviewBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new Viewholdear(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholdear holder, int position) {
        Reviews reviews =reviewsList.get(position);

        holder.binding.textAuthor.setText(reviews.getAuthorName());
        holder.binding.textContent.setText(reviews.getContent());
    }

    @Override
    public int getItemCount() {
        if (reviewsList!=null){
            return reviewsList.size();
        }
        return 0;

    }

    public class Viewholdear extends RecyclerView.ViewHolder {
        CustomeItemRecReviewBinding binding;
        public Viewholdear(@NonNull CustomeItemRecReviewBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
    }
}

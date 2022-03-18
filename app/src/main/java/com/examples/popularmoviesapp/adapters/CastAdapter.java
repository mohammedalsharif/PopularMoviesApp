package com.examples.popularmoviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.databinding.CustomeItemCastMovieBinding;
import com.examples.popularmoviesapp.model.Cast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ViewHcast> {
    List<Cast> castList;

    public CastAdapter() {
        this.castList = new ArrayList<>();
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHcast onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomeItemCastMovieBinding binding =
                CustomeItemCastMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHcast(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHcast holder, int position) {
        Cast cast= castList.get(position);
        Picasso.get().load(cast.getProfilePath()).into(holder.binding.imageCastProfile);
        holder.binding.textCastName.setText(cast.getName());
    }

    @Override
    public int getItemCount() {
        return castList != null ? castList.size() : 0;
    }

    public class ViewHcast extends RecyclerView.ViewHolder {
        CustomeItemCastMovieBinding binding;

        public ViewHcast(@NonNull CustomeItemCastMovieBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

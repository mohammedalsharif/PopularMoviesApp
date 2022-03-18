package com.examples.popularmoviesapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.popularmoviesapp.databinding.CustomeItemRecTailerBinding;
import com.examples.popularmoviesapp.model.Trailer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolerT> {
    List<Trailer> trailerList;
    Context context;

    public TrailerAdapter(Context context) {
        this.trailerList = new ArrayList<>();
        this.context=context;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolerT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomeItemRecTailerBinding binding = CustomeItemRecTailerBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolerT(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolerT holder, int position) {
        Trailer trailer= trailerList.get(position);
        Picasso.get().load(trailer.getImageTrailer()).into(holder.binding.imageTrailer);
        holder.binding.trailerName.setText(trailer.getName());
        holder.binding.cardTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_VIEW,Uri.parse(trailer.getUrlWatchTrailer()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailerList != null ? trailerList.size() : 0;
    }

    public class ViewHolerT extends RecyclerView.ViewHolder {
        CustomeItemRecTailerBinding binding;

        public ViewHolerT(@NonNull CustomeItemRecTailerBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}

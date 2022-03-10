package com.examples.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.examples.popularmoviesapp.adapter.MoviesAdapter;
import com.examples.popularmoviesapp.databinding.ActivityMainBinding;
import com.examples.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        iniRec();

    }

    private void iniRec() {
        List<Movie> movies = getMovieData();
        MoviesAdapter adapter =new MoviesAdapter(movies);
        binding.recyclerViewMain.setAdapter(adapter);
        binding.recyclerViewMain.setLayoutManager(new GridLayoutManager(this,3));
        binding.recyclerViewMain.setHasFixedSize(true);
    }

    private List<Movie> getMovieData() {
        List<Movie>moviesList=new ArrayList<>();
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
      moviesList.add(new Movie(R.drawable.ic_launcher_background,"Movie Name"));
        return moviesList;

    }
}
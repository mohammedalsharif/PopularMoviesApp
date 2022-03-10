package com.examples.popularmoviesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.adapter.MoviesAdapter;
import com.examples.popularmoviesapp.adapter.MoviesListener;
import com.examples.popularmoviesapp.databinding.ActivityMainBinding;
import com.examples.popularmoviesapp.model.Movie;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DiscoverFragment discoverFragment = new DiscoverFragment();
    FavoritesFragment favoritesFragment =new FavoritesFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,discoverFragment).commit();
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.discover_item:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,discoverFragment).commit();
                        return true;
                    case R.id.favorites_item:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,favoritesFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
}
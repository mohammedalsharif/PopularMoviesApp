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
import android.widget.Toast;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.Utils.ActivityUtils;
import com.examples.popularmoviesapp.adapter.MoviesAdapter;
import com.examples.popularmoviesapp.adapter.MoviesListener;
import com.examples.popularmoviesapp.databinding.ActivityMainBinding;
import com.examples.popularmoviesapp.model.Movie;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MovieViewModel mViewModel ;
    DiscoverFragment discoverFragment = new DiscoverFragment();
    FavoritesFragment favoritesFragment =new FavoritesFragment();
    SearchFragment searchFragment =new SearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),discoverFragment,R.id.main_container);
        iniBottomNav();
    }

    private void iniBottomNav() {
        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {//R.id.main_container,discoverFragment)
                switch (item.getItemId()){
                    case R.id.discover_item:
                        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),discoverFragment,R.id.main_container);
                        return true;
                    case R.id.favorites_item:
                        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),favoritesFragment,R.id.main_container);
                        return true;
                    case R.id.search_item:
                        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),searchFragment,R.id.main_container);
                        return true;
                }
                return false;
            }
        });
    }




}
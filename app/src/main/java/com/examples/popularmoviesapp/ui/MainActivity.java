package com.examples.popularmoviesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.Utils.ActivityUtils;
import com.examples.popularmoviesapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import coil.ImageLoader;
import coil.ImageLoaders;
import coil.request.ImageRequest;
import coil.request.ImageResult;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DiscoverFragment discoverFragment = new DiscoverFragment();
    FavoritesFragment favoritesFragment =new FavoritesFragment();


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
                }
                return false;
            }
        });
    }




}
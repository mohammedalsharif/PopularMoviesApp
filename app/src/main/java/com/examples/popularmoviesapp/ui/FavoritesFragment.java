package com.examples.popularmoviesapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.adapters.FavoriteAdapter;
import com.examples.popularmoviesapp.data.database.DataViewModel;
import com.examples.popularmoviesapp.databinding.FragmentFavortisBinding;
import com.examples.popularmoviesapp.model.FavoriteMovie;
import com.examples.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {
    DataViewModel dataViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavortisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FavoriteAdapter adapter = new FavoriteAdapter();
        FragmentFavortisBinding binding = FragmentFavortisBinding.inflate(getLayoutInflater());
        dataViewModel = new ViewModelProvider(this).get(DataViewModel.class);
        dataViewModel.getAllFavoriteMovies().observe(getActivity(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setFavoriteMovieList(movies);
            }
        });
        binding.recFavorite.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recFavorite.setHasFixedSize(true);
        binding.recFavorite.setAdapter(adapter);


        return binding.getRoot();
    }
}
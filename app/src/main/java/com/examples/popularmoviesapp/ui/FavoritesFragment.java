package com.examples.popularmoviesapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.adapters.FavoriteAdapter;
import com.examples.popularmoviesapp.databinding.FragmentFavortisBinding;
import com.examples.popularmoviesapp.model.FavoriteMovie;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

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
        FragmentFavortisBinding binding =FragmentFavortisBinding.inflate(getLayoutInflater());
        List<FavoriteMovie>listFav=new ArrayList<>();
        listFav.add(new FavoriteMovie("Mohammed"));
        listFav.add(new FavoriteMovie("Mohammed"));
        listFav.add(new FavoriteMovie("Mohammed"));
        listFav.add(new FavoriteMovie("Mohammed"));
        listFav.add(new FavoriteMovie("Mohammed"));
        listFav.add(new FavoriteMovie("Mohammed"));
        listFav.add(new FavoriteMovie("Mohammed"));
        listFav.add(new FavoriteMovie("Mohammed"));
        FavoriteAdapter adapter =new FavoriteAdapter(listFav);
        binding.recFavorite.setLayoutManager(new GridLayoutManager(getActivity(),2));
        binding.recFavorite.setHasFixedSize(true);
        binding.recFavorite.setAdapter(adapter);




        return binding.getRoot();
    }
}
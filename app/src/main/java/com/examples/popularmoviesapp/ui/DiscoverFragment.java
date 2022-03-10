package com.examples.popularmoviesapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examples.popularmoviesapp.R;
import com.examples.popularmoviesapp.adapter.MoviesAdapter;
import com.examples.popularmoviesapp.adapter.MoviesListener;
import com.examples.popularmoviesapp.databinding.FragmentDiscoverBinding;
import com.examples.popularmoviesapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiscoverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiscoverFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiscoverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
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
        FragmentDiscoverBinding binding = FragmentDiscoverBinding.inflate(getLayoutInflater());
        List<Movie> movies = getMovieData();
        MoviesAdapter adapter = new MoviesAdapter(movies, getActivity(),new MoviesListener() {
            @Override
            public void OnClickItemRec(int position) {

            }
        });
        binding.recyclerViewDiscover.setAdapter(adapter);
        binding.recyclerViewDiscover.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        binding.recyclerViewDiscover.setHasFixedSize(true);
        return binding.getRoot();
    }

    //        iniRec();
//    private void iniRec() {
//
//
//    }
    private List<Movie> getMovieData() {
        List<Movie> moviesList = new ArrayList<>();
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));
        moviesList.add(new Movie(R.drawable.film, "Movie Name"));

        return moviesList;

    }

}
package com.examples.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.GET;

public class VideosResponse {
    @SerializedName("results")
    @Expose
    List<Trailer> trailerList;

    public VideosResponse() {
    }

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    public void setTrailerList(List<Trailer> trailerList) {
        this.trailerList = trailerList;
    }
}

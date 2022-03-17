package com.examples.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.http.Query;

public class ReviewsResponse {

    @SerializedName("results")
    @Expose
    private List<Reviews> results;

    public ReviewsResponse() {
    }

    public List<Reviews> getResults() {
        return results;
    }

    public void setResults(List<Reviews> results) {
        this.results = results;
    }
}

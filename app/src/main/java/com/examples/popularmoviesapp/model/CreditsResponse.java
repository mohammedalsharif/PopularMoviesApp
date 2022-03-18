package com.examples.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreditsResponse {

    @SerializedName("cast")
    @Expose
    private List<Cast> castList ;

    public CreditsResponse() {
    }

    public List<Cast> getCastList() {
        return castList;
    }

    public void setCastList(List<Cast> castList) {
        this.castList = castList;
    }
}

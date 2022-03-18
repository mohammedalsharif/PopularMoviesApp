package com.examples.popularmoviesapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("site")
    @Expose
    private String site;

    public Trailer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }
    public String getImageTrailer(){
        return"https://img.youtube.com/vi/"+key+"/hqdefault.jpg";
    }
    public String getUrlWatchTrailer(){
       return "http://www.youtube.com/watch?v="+key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}

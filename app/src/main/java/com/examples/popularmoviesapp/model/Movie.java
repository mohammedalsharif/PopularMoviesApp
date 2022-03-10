package com.examples.popularmoviesapp.model;

public class Movie {
    private int image;
    private String movieName;

    public Movie(int image, String movieName) {
        this.image = image;
        this.movieName = movieName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
}

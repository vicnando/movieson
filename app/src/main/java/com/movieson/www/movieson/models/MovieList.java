package com.movieson.www.movieson.models;

import java.util.ArrayList;

/**
 * Created by microsoft on 5/22/2017.
 */

public class MovieList {

    ArrayList<Movie> movies = new ArrayList<>();

    private String posterPath;
    private String title;
    private String releaseDate;
    //private int runtime;
    private String voteAverage;
    private String overview;
    private String id;


    public MovieList(String posterPath, String title) {
        this.posterPath = posterPath;
        this.title = title;
    }

    public MovieList(String posterPath, String title, String releaseDate,  String voteAverage, String overview, String id) {
        this.posterPath = posterPath;
        this.title = title;
        this.releaseDate = releaseDate;
        //this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.id = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    /*public int getRuntime() {
        return runtime;
    }*/

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }
}

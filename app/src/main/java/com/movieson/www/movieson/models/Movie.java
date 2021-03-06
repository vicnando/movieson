package com.movieson.www.movieson.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by microsoft on 5/22/2017.
 */

public class Movie {
    private  String movieid;
    private String title;
    private String posterPath;
    private String releaseDate;
    private int runtime;
    private String voteAverage;
    private String overview;

    public Movie(String movieid, String title, String posterPath, String releaseDate, int runtime,
                 String voteAverage, String overview) {
        this.movieid = movieid;
        this.title = title;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.voteAverage = voteAverage;
        this.overview = overview;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }



    public void setReleaseDate(String releaseDate) {

        this.releaseDate = releaseDate;
    }



}

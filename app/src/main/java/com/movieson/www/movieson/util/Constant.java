package com.movieson.www.movieson.util;

/**
 * Created by microsoft on 5/22/2017.
 */

public class Constant {

    String URL_DETAIL ;
    final String URL_BASE = "https://api.themoviedb.org/3/";

  /*  https://api.themoviedb.org/3/movie/popular?&api_key=8f46b8971e3e45727f003a5a5dda87b7*/
    final String URL_BASE_IMAGE = "http://image.tmdb.org/t/p/w185/";

    final String URL_POPULAR_MOVIE = "discover/movie?sort_by=popularity.desc";
    final String URL_PUPULAR_KID_MOVIE = "discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc";
    final String URL_MOST_RATED_MOVIE = "discover/movie/?certification_country=US&certification=R&sort_by=vote_average.desc";

    final String URL_SEARCH_ID = "movie/";
    final String URL_SEARCH = "search/movie?";

    final String URL_POPULAR = "popular?";
    final String URL_TOPRATE = "top_rate?";
    final String URL_UPCOMING = "upcoming?";

    final String URL_API_KEY = "&api_key=8f46b8971e3e45727f003a5a5dda87b7";
    final String URL_PAGE = "&page=";
    final String URL_QUERY = "&query=";

    private String MOVIE_POPULAR_URL;
    private String MOVIE_TOPRATE_URL;
    private String MOVIE_COMINGSOON_URL;

    String SEARCH_URL =URL_BASE + URL_SEARCH + URL_API_KEY;

    public String getMOVIE_POPULAR_URL() {
        this.MOVIE_POPULAR_URL = URL_BASE + URL_SEARCH_ID + URL_POPULAR + URL_API_KEY + URL_PAGE;
        return MOVIE_POPULAR_URL;
    }

    public String getURL_DETAIL(String movieID){
        this.URL_DETAIL = URL_BASE + URL_SEARCH_ID + movieID + "?" +URL_API_KEY;
        return URL_DETAIL;
    }

    public String getMOVIE_TOPRATE_URL() {
        this.MOVIE_TOPRATE_URL = URL_BASE + URL_SEARCH_ID + URL_TOPRATE + URL_API_KEY + URL_PAGE;
        return MOVIE_TOPRATE_URL;
    }

    public String getMOVIE_COMINGSOON_URL() {
        this.MOVIE_COMINGSOON_URL =  URL_BASE + URL_SEARCH_ID + URL_UPCOMING + URL_API_KEY + URL_PAGE;
        return MOVIE_COMINGSOON_URL;
    }

    public String getURL_BASE_IMAGE() {
        return URL_BASE_IMAGE;
    }

    public String getSEARCH_URL() {
        return SEARCH_URL;
    }

    public void setSEARCH_URL(String query, int page) {

        this.SEARCH_URL = URL_BASE + URL_SEARCH + URL_API_KEY + URL_QUERY + query + URL_PAGE + String.valueOf(page);
    }


}

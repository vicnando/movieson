package com.movieson.www.movieson.util;

/**
 * Created by microsoft on 5/22/2017.
 */

public class Constant {

    final String URL_BASE = "https://api.themoviedb.org/3/";
    final String URL_BASE_IMAGE = "http://image.tmdb.org/t/p/w185/";
    final String URL_POPULAR_MOVIE = "discover/movie?sort_by=popularity.desc";
    final String URL_PUPULAR_KID_MOVIE = "discover/movie?certification_country=US&certification.lte=G&sort_by=popularity.desc";
    final String URL_MOST_RATED_MOVIE = "discover/movie/?certification_country=US&certification=R&sort_by=vote_average.desc";
    final String URL_SEARCH_ID = "movie/";
    final String URL_API_KEY = "&api_key=2bea38317c7da072ccff5b9ad2bcc5a2";

}

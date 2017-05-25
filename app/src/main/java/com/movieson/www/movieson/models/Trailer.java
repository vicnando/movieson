package com.movieson.www.movieson.models;

/**
 * Created by microsoft on 5/25/2017.
 */

public class Trailer {
    String key;

    public Trailer(String key) {

        this.key = key;
    }


    public void setKey(String key) {
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }

}

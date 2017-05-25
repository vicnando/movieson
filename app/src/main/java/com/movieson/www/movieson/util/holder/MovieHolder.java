package com.movieson.www.movieson.util.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieson.www.movieson.R;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.util.Constant;
import com.squareup.picasso.Picasso;

/**
 * Created by microsoft on 5/22/2017.
 */

public class MovieHolder extends RecyclerView.ViewHolder {
    Constant constant;

    private ImageView imgMovie;
    private TextView title;
    private Context context;

    public MovieHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;
        this.imgMovie = (ImageView)itemView.findViewById(R.id.imageMovie);
        this.title = (TextView)itemView.findViewById(R.id.titleMovie);
    }

    public void updateUI(MovieList movieLists){
        final String URL_IMAGE = "http://image.tmdb.org/t/p/w185" +movieLists.getPosterPath();
        Picasso.with(context).load(URL_IMAGE).into(imgMovie);
        title.setText(movieLists.getTitle());
    }
}

package com.movieson.www.movieson.util.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieson.www.movieson.R;
import com.movieson.www.movieson.activity.DetailMovieActivity;
import com.movieson.www.movieson.models.Movie;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.util.holder.MovieHolder;

import java.util.ArrayList;

/**
 * Created by microsoft on 5/22/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder>{
    private ArrayList<MovieList> movieListArrayList;
    private Context context;

    public MovieAdapter(Context context) {this.context = context.getApplicationContext();}

    public MovieAdapter(ArrayList<MovieList> movieListArrayList){
        this.movieListArrayList = movieListArrayList;
    }

    @Override

    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie_list, parent, false);
        return  new MovieHolder(card, parent.getContext());

    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {
        final MovieList movieList = movieListArrayList.get(position);
        holder.updateUI(movieList);
        Log.d("as", movieList.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),DetailMovieActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", movieList.getId());
                v.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieListArrayList.size();
    }
}

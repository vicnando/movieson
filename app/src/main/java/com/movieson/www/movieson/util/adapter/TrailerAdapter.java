package com.movieson.www.movieson.util.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieson.www.movieson.R;
import com.movieson.www.movieson.activity.DetailMovieActivity;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.models.Trailer;
import com.movieson.www.movieson.util.holder.MovieHolder;
import com.movieson.www.movieson.util.holder.TrailerHolder;

import java.util.ArrayList;

/**
 * Created by microsoft on 5/22/2017.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerHolder>{
    private ArrayList<Trailer> trailerArrayList;
    private Context context;

    public TrailerAdapter(Context context) {this.context = context.getApplicationContext();}

    public TrailerAdapter(ArrayList<Trailer> trailerArrayList){
        this.trailerArrayList = trailerArrayList;

    }

    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_trailer_recycler, parent, false);
        return  new TrailerHolder(card, parent.getContext());

    }

    @Override
    public void onBindViewHolder(TrailerHolder holder, final int position) {
        final Trailer trailer = trailerArrayList.get(position);
        holder.updateUI(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v="+trailer.getKey()));
                v.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trailerArrayList.size();
    }
}


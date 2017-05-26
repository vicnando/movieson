package com.movieson.www.movieson.util.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieson.www.movieson.R;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.models.Review;
import com.movieson.www.movieson.util.holder.MovieHolder;
import com.movieson.www.movieson.util.holder.ReviewHolder;

import java.util.ArrayList;

/**
 * Created by microsoft on 5/26/2017.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewHolder>{
    ArrayList<Review> reviewArrayList = new ArrayList<>();
    Context context;

    public ReviewAdapter(Context context) {this.context = context.getApplicationContext();}

    public ReviewAdapter(ArrayList<Review> reviewArrayList){
        this.reviewArrayList = reviewArrayList;
    }
    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_recycler, parent, false);
        return  new ReviewHolder(card, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        Review review = reviewArrayList.get(position);
        holder.updateUI(review);
    }

    @Override
    public int getItemCount() {
        return reviewArrayList.size();
    }
}


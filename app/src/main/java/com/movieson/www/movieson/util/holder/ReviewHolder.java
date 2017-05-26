package com.movieson.www.movieson.util.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.movieson.www.movieson.R;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.models.Review;
import com.squareup.picasso.Picasso;

/**
 * Created by microsoft on 5/26/2017.
 */

public class ReviewHolder extends RecyclerView.ViewHolder{

    private TextView author,content;
    private Context context;

    public ReviewHolder(View itemView, Context context) {
            super(itemView);

            this.context = context;
            this.author = (TextView)itemView.findViewById(R.id.tvauthor);
            this.content = (TextView)itemView.findViewById(R.id.tvcontent);
    }

    public void updateUI(Review review){
        author.setText(review.getAuthor());
        content.setText(review.getReview());
    }
}

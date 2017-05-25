package com.movieson.www.movieson.util.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieson.www.movieson.R;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.models.Trailer;
import com.squareup.picasso.Picasso;

/**
 * Created by microsoft on 5/22/2017.
 */

public class TrailerHolder extends RecyclerView.ViewHolder {
    Context context;
    TextView trailer;

    public TrailerHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        trailer = (TextView) itemView.findViewById(R.id.trailer);
    }


    public void updateUI(int i){
        trailer.setText("Trailer" + String.valueOf(i));
    }

}

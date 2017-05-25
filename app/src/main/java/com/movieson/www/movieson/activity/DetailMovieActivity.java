package com.movieson.www.movieson.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.movieson.www.movieson.R;
import com.movieson.www.movieson.models.Movie;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.models.Trailer;
import com.movieson.www.movieson.util.Constant;
import com.movieson.www.movieson.util.adapter.MovieAdapter;
import com.movieson.www.movieson.util.adapter.TrailerAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetailMovieActivity extends AppCompatActivity {

    ArrayList<Trailer> trailerArrayAdapter = new ArrayList<>();
    Constant constant;
    CardView cardView;
    ImageView imgMovie;
    LinearLayout ll;

    Movie movie;

    RecyclerView recyclerView;
    TrailerAdapter trailerAdapter;


    TextView tvtitle, tvoverview, tvduration, tvrating;

    final String URL_BASE_IMAGE = "http://image.tmdb.org/t/p/w500/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutmanager);

        imgMovie = (ImageView) findViewById(R.id.imgMovie);
        ll = (LinearLayout) findViewById(R.id.ll);
        Intent i = getIntent();
        String movieID = i.getStringExtra("id");

        tvtitle = (TextView) findViewById(R.id.tvtitle);
        tvoverview = (TextView) findViewById(R.id.tvoverview);
        tvduration = (TextView) findViewById(R.id.tvduration);
        tvrating = (TextView) findViewById(R.id.tvrating);

        constant = new Constant();

        detailRequest(movieID);

    }

    private void detailRequest(String movieID){


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
            (Request.Method.GET, constant.getURL_DETAIL(movieID)+"&append_to_response=videos", null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                try {
                    Log.d("response", response.toString());
                    String title = response.getString("title");
                    String overview = response.getString("overview");
                    String releasedate = response.getString("release_date");
                    String poster = response.getString("poster_path");
                    String votecount = response.getString("vote_count");
                    String voteaverage = response.getString("vote_average");
                    String backdrop = response.getString("backdrop_path");
                    int runtime = response.getInt("runtime");

                    JSONObject videoobject= response.getJSONObject("videos");
                    JSONArray resultarray = videoobject.getJSONArray("results");

                    for(int i = 0 ; i<resultarray.length();i++){
                        JSONObject  resultobject = resultarray.getJSONObject(i);
                        String key = resultobject.getString("key");
                        Log.d("key",key);
                        Trailer trailer = new Trailer(key);
                        trailerArrayAdapter.add(trailer);
                    }

                    trailerAdapter = new TrailerAdapter(trailerArrayAdapter);
                    recyclerView.setAdapter(trailerAdapter);


                    SimpleDateFormat formatDefault = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");



                    String year = getYear(releasedate);

                    tvtitle.setText(title + " ("+year+")");
                    tvoverview.setText(overview);
                    tvrating.setText(voteaverage + "/10");
                    tvduration.setText(String.valueOf(runtime)+" min");

                    final String posterurl = "http://image.tmdb.org/t/p/w185" +poster;
                    final String backdropurl = "http://image.tmdb.org/t/p/w500" +backdrop;
                    Picasso.with(DetailMovieActivity.this).load(posterurl).into(imgMovie);
                    Picasso.with(DetailMovieActivity.this)
                            .load(backdropurl)
                            .resize(500, 800)
                            .centerCrop()
                            .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                           ll.setBackground(new BitmapDrawable
                                    (DetailMovieActivity.this.getResources(), bitmap));
                        }

                        @Override
                        public void onBitmapFailed(Drawable errorDrawable) {
                        Log.d("error", "error");
                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {
                            Log.d("load","asd");
                        }
                    });

                } catch (JSONException e) {
                    Log.i("Error", e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(DetailMovieActivity.this).add(jsonObjectRequest);

    }


public String getYear(String releaseDate) {
    SimpleDateFormat formatDefault = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");

    try {
        Date YearFormat = formatDefault.parse(releaseDate);
        releaseDate = formatYear.format(YearFormat);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return  releaseDate;
}



}

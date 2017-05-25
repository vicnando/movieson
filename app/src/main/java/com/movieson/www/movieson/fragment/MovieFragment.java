package com.movieson.www.movieson.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.movieson.www.movieson.R;
import com.movieson.www.movieson.models.MovieList;
import com.movieson.www.movieson.util.Constant;
import com.movieson.www.movieson.util.adapter.MovieAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment {
    Constant constant;

    private ArrayList<MovieList> movieListArrayList = new ArrayList<>();
    Spinner etpage;
    ImageButton prev, next;
    RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    int totalpage;
    ArrayList<Integer> spinpagelist = new ArrayList<Integer>() ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MovieFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieFragment newInstance(String param1, String param2) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie, container, false);
        prev = (ImageButton) v.findViewById(R.id.prev);
        next = (ImageButton) v.findViewById(R.id.next);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutmanager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(mLayoutmanager);
        etpage = (Spinner) v.findViewById(R.id.etpagenav);
        constant = new Constant();

        spinpagelist.add(1);

        final ArrayAdapter<Integer> spinnerarrayadapter= new ArrayAdapter<Integer>
                (getContext(),android.R.layout.simple_spinner_dropdown_item,spinpagelist);
        etpage.setAdapter(spinnerarrayadapter);

        requestPopularMovie(1);
        spinnerarrayadapter.notifyDataSetChanged();

        setVIsibility();

        etpage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                requestPopularMovie(spinpagelist.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer a = Integer.parseInt(etpage.getSelectedItem().toString());
                Integer b = a - 1 - 1 ;

                etpage.setSelection(b);
                Log.d("spinner", String.valueOf(a)+ String.valueOf(b) + etpage.getSelectedItem().toString());

                Log.d("spinner", etpage.getSelectedItem().toString());

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer x = Integer.parseInt(etpage.getSelectedItem().toString());
                Integer z = x ;
                etpage.setSelection(z);
                Log.d("spinner", String.valueOf(x)+ String.valueOf(z) + etpage.getSelectedItem().toString());
            }
        });

//        requestPopularMovie(int.parseInt(etpage.getText().toString()));



        // Inflate the layout for this fragment
        return v;
    }

    public void requestPopularMovie(int i){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, constant.getMOVIE_POPULAR_URL()+"&page="+i, null,
                        new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("response", response.toString());
                    movieListArrayList.clear();
                    spinpagelist.clear();

                    JSONArray result = response.getJSONArray("results");
                    for (int i = 0; i<result.length(); i++) {
                        MovieList movieListsDetail = objectModelMovie(response, i);
                        movieListArrayList.add(movieListsDetail);

                    }

                    totalpage = response.getInt("total_pages");
                    for(int j = 1; j<= totalpage; j++){
                        spinpagelist.add(j);
                    }
                    movieAdapter = new MovieAdapter(movieListArrayList);
                    recyclerView.setAdapter(movieAdapter);

                    setVIsibility();
                    Log.d("page",String.valueOf(totalpage));

                } catch (JSONException e) {
                    Log.i("Error", e.getLocalizedMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);

    }

    @NonNull
    private MovieList objectModelMovie(JSONObject response, int x) throws JSONException {
        JSONArray result = response.getJSONArray("results");
        JSONObject object = result.getJSONObject(x);

        String poster = object.getString("poster_path");
        String title = object.getString("title");

        String relaseDate = object.getString("release_date");
        //Integer runtime = object.getInt("runtime");
        String voteAvarage = object.getString("vote_average");
        String overview = object.getString("overview");
        String id = object.getString("id");

        //MovieLists movieLists = new MovieLists(poster, title);
        return new MovieList(poster, title, relaseDate, voteAvarage, overview, id );
    }

    public void setVIsibility(){
        if(etpage.getSelectedItem().equals(1)){
            prev.setVisibility(View.INVISIBLE);
        }else {
            prev.setVisibility(View.VISIBLE);
        }

        if(etpage.getSelectedItem().equals(totalpage)){
            next.setVisibility(View.INVISIBLE);
        }else {
            next.setVisibility(View.VISIBLE);
        }
    }



}

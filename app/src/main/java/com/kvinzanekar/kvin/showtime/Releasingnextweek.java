package com.kvinzanekar.kvin.showtime;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kvinzanekar.kvin.showtime.Data.CustomListViewAdapter;
import com.kvinzanekar.kvin.showtime.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Releasingnextweek extends Fragment {

    //private String url = "https://api.cinemalytics.com/v1/movie/upcoming?auth_token=8FD6E7B756BA7FB39EB82C99A4B570F0";
    //private String url = "http://data.tmsapi.com/v1.1/movies/showings?startDate=2016-05-25&zip=75252&api_key=kkuc3eefhd9nd43qb6v2z8ny";
    //private String kvinapi = "http://www.kvinzanekar.com/webservices/api/v1/movie-list";
    private String releasingNextWeek = "http://www.kvinzanekar.com/webservices/api/v1/releasing-next-week";
    //private String tmsapi = "http://data.tmsapi.com/v1.1/movies/showings?startDate=2016-05-27&zip=78701&api_key=vnzjehsh64w8gpy6p73ncp76";
    private CustomListViewAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private ListView listView;
    //public static Button showTimes;
    public static final String JSON_ARRAY = "";
    public static final String TITLE = "title";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "release_date";
    public static final String IMAGE_URL = "poster_path";
    public static final String HTTP_URL = "http://image.tmdb.org/t/p/w500";
    public static final String RATING_BAR = "popularity";
    public static final String MOVIE_ID = "tmsId";
    public static final String relativeUrl = "http://www.kvinzanekar.com/webservices/sites/default/files/";

    private JSONArray results = null;

    private String json;


    public Releasingnextweek() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_releasingnextweek, container, false);
        listView = (ListView)rootView.findViewById(R.id.list);
        adapter = new CustomListViewAdapter(getActivity(), R.layout.list_row, movies);
        listView.setAdapter(adapter);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, releasingNextWeek, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        String movieId = jsonObject.getString("field_movie_id");
                        //Log.v("Movie Id is " , movieId);
                        String title = jsonObject.getString("title");
                        //Log.v("Title is ", title);
                        String description = jsonObject.getString("field_plot");
                        // Log.v("Description is ", description);
                        String imageUrl = jsonObject.getJSONObject("field_movie_image").getString("filename");
                        imageUrl = relativeUrl + imageUrl;
                        //Log.v("ImageUrl is " , relativeUrl + imageUrl);
                        //String releaseDate = jsonObject.getString("releaseDate");
                        //Log.v("Relase Date is" ,releaseDate);
                        String genre = jsonObject.getString("field_genre");
                        // Log.v("Genre ",genre);
                        String ratings = jsonObject.getString("field_ratings");
                        String youtube_url = jsonObject.getJSONObject("field_youtube_trailer").getString("url");
                        //Log.v("Youtube Url is ", youtube_url);
                        Movie movie = new Movie();
                        movie.setTitle(title);
                        movie.setDescription(description);
                        //movie.setReleaseDate(releaseDate);
                        movie.setGenre(genre);
                        movie.setThumbnail(imageUrl);
                        movie.setRating(ratings);

                        if(youtube_url.length()!=0) {
                            movie.setYurl(youtube_url);
                        }


                        movies.add(movie);


                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(arrayRequest);

        if(movies.size()>0)
        {
            movies.clear();
        }
        return rootView;
    }

}

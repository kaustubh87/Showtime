package com.kvinzanekar.kvin.showtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity{



    //private String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=f89136f312b2a24e23a0608ddbe3dc81";
    //private String url = "http://data.tmsapi.com/v1.1/movies/showings?startDate=2016-05-25&zip=75252&api_key=kkuc3eefhd9nd43qb6v2z8ny";
    private String kvinapi = "http://www.kvinzanekar.com/webservices/api/v1/movie-list";
    //private String tmsapi = "http://data.tmsapi.com/v1.1/movies/showings?startDate=2016-05-27&zip=78701&api_key=vnzjehsh64w8gpy6p73ncp76";
    private CustomListViewAdapter adapter;
    private ArrayList<Movie> movies = new ArrayList<>();
    private ListView listView;
    public static Button showTimes;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListViewAdapter(MainActivity.this, R.layout.list_row, movies);
        listView.setAdapter(adapter);

        showTimes = (Button)findViewById(R.id.showTimes);
        showTimes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ShowTimeActivity.class);
                startActivity(intent);
            }
        });



       /* StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            results = jsonObject.getJSONArray(JSON_ARRAY);

                            title = new String[results.length()];

                            overview = new String[results.length()];
                            release_date = new String[results.length()];
                            images = new String[results.length()];
                            ratings = new String[results.length()];



                            for(int i=0;i<results.length();i++){
                                JSONObject jo = results.getJSONObject(i);
                                title[i] = jo.getString(TITLE);
                                //Log.v("Title is " ,jo.getString(TITLE));
                               // overview[i] = jo.getString(OVERVIEW);
                               // release_date[i] = jo.getString(RELEASE_DATE);
                                //images[i] = jo.getString(IMAGE_URL);
                                //ratings[i] = jo.getString(RATING_BAR);


                                Movie movie = new Movie();
                                movie.setTitle(title[i]);
                                //movie.setDescription(overview[i]);
                                //movie.setReleaseDate(release_date[i]);
                                //movie.setThumbnail(HTTP_URL + images[i]);
                                //Log.v("Image url is ", HTTP_URL + images[i]);
                                //Log.v("Rating is " ,ratings[i]);
                                //movie.setRating(ratings[i]);

                                movies.add(movie);

                                adapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

*/
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, kvinapi, new Response.Listener<JSONArray>() {
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

/*
        JsonArrayRequest arrayRequest2 = new JsonArrayRequest(tmsapi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String movieId = jsonObject.getString("tmsId");
                        //Log.v("tmsId is ", movieId);
                        //String description = jsonObject.getString("field_plot");
                        //Log.v("Description is ", description);
                        // String imageUrl = jsonObject.getString("PosterPath");
                        //Log.v("ImageUrl is " ,imageUrl);
                        //String releaseDate = jsonObject.getString("releaseDate");
                        //Log.v("Relase Date is" ,releaseDate);
                        //String genre = jsonObject.getString("field_genre");
                        //Log.v("Genre ",genre);

                        //String yurl = jsonObject.getString("TrailerLink");
                        //Log.v("Yurl is " ,yurl);

                        if(movieId.equalsIgnoreCase(jsonObject1.getString("field_movie_id")))
                        {

                            Log.v("tmsId is ", movieId);
                        }
                        else
                        {

                        }


                        Movie movie = new Movie();
                        movie.setTitle(movieId);
                        //movie.setDescription(description);
                        //movie.setReleaseDate(releaseDate);
                        //movie.setGenre(genre);
                        //movie.setThumbnail(imageUrl);

                     if(yurl.length()!=0) {
                         movie.setYurl(yurl);
                     }


                        movies.add(movie);


                        adapter.notifyDataSetChanged();

                    }


                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
*/


        AppController.getInstance().addToRequestQueue(arrayRequest);

        // AppController.getInstance().addToRequestQueue(arrayRequest2);


    }


}

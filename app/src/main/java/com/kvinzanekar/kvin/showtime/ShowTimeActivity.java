package com.kvinzanekar.kvin.showtime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kvinzanekar.kvin.showtime.Data.CustomListViewAdapter;
import com.kvinzanekar.kvin.showtime.Data.CustomShowTimeViewAdapter;
import com.kvinzanekar.kvin.showtime.Model.ShowTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShowTimeActivity extends AppCompatActivity {

    private String tmsapi = "http://data.tmsapi.com/v1.1/movies/showings?startDate=2016-06-01&zip=75252&api_key=vnzjehsh64w8gpy6p73ncp76";
    private CustomShowTimeViewAdapter adapter;
    private ListView listView;
    private ArrayList<ShowTime> showTimeArrayList = new ArrayList<>();
    public static JSONArray showtimes = null;
    public static final String SHOWTIME = "showtimes";
    public static final String TICKET_URL = "ticketURI";
    public static final String THEATER_NAME = "name";
    public static final String THEATRE = "theatre";
    public String ticketUrl, theatreName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.showtime_list);
        adapter = new CustomShowTimeViewAdapter(ShowTimeActivity.this, R.layout.showtime_row, showTimeArrayList);
        listView.setAdapter(adapter);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, tmsapi, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        showtimes = jsonObject.getJSONArray(SHOWTIME);
                        for (int j = 0; j < showtimes.length(); j++) {
                            JSONObject jobj = showtimes.getJSONObject(j);
                            theatreName = jobj.getJSONObject(THEATRE).getString(THEATER_NAME);
                            ticketUrl = jobj.getString(TICKET_URL);

                        }

                        //Log.v("Theater is " , theatreName);
                        //Log.v("Ticket url is " , ticketUrl);

                        ShowTime showTime = new ShowTime();
                        showTime.setTicketUrl(ticketUrl);
                        showTime.setTheaterName(theatreName);
                        showTimeArrayList.add(showTime);
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

    }

}

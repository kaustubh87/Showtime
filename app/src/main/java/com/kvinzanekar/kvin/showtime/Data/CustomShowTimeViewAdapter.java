package com.kvinzanekar.kvin.showtime.Data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.kvinzanekar.kvin.showtime.Model.Movie;
import com.kvinzanekar.kvin.showtime.Model.ShowTime;
import com.kvinzanekar.kvin.showtime.R;

import java.util.ArrayList;
import java.util.logging.SocketHandler;

/**
 * Created by kvin on 6/1/16.
 */
public class CustomShowTimeViewAdapter extends ArrayAdapter<ShowTime> {

    private LayoutInflater inflater;
    private ArrayList<ShowTime> data;
    private Activity mContext;
    private int layoutResourceId;

    public CustomShowTimeViewAdapter(Activity context, int resource, ArrayList<ShowTime> objs) {
        super(context, resource, objs);
        data = objs;
        mContext = context;
        layoutResourceId = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public ShowTime getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(ShowTime item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder viewHolder = null;
        if (row == null) {
            inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(layoutResourceId, parent, false);
            viewHolder = new ViewHolder();

            // Get References to our widgets.

            viewHolder.theaterName = (TextView)row.findViewById(R.id.theatreName);
            viewHolder.ticketUrl = (TextView)row.findViewById(R.id.ticketUrl);
            row.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) row.getTag();
        }

        viewHolder.showtime = data.get(position);
        viewHolder.theaterName.setText(viewHolder.showtime.getTheaterName());
        viewHolder.ticketUrl.setText(viewHolder.showtime.getTicketUrl());







        return row;

    }

    public class ViewHolder {

        ShowTime showtime;
        TextView theaterName, ticketUrl;
    }





}

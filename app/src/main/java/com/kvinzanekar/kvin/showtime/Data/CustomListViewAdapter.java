package com.kvinzanekar.kvin.showtime.Data;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.kvinzanekar.kvin.showtime.AppController;
import com.kvinzanekar.kvin.showtime.DetailsActivity;
import com.kvinzanekar.kvin.showtime.Model.Movie;
import com.kvinzanekar.kvin.showtime.R;
import com.kvinzanekar.kvin.showtime.VideoActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by kvin on 5/25/16.
 */
public class CustomListViewAdapter extends ArrayAdapter<Movie> {


    private LayoutInflater inflater;
    private ArrayList<Movie> data;
    private Activity mContext;
    private int layoutResourceId;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListViewAdapter(Activity context, int resource, ArrayList<Movie> objs) {
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
    public Movie getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(Movie item) {
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


            // Get References to our views.

            viewHolder.thumbnail = (NetworkImageView) row.findViewById(R.id.image);
            viewHolder.title = (TextView) row.findViewById(R.id.title);
            viewHolder.description = (TextView) row.findViewById(R.id.description);
            viewHolder.releaseDate = (TextView) row.findViewById(R.id.releaseDate);
            viewHolder.genre = (TextView) row.findViewById(R.id.genre);
            viewHolder.url = (TextView) row.findViewById(R.id.video);
            viewHolder.ratingBar = (RatingBar) row.findViewById(R.id.rating);
            Drawable progress = viewHolder.ratingBar.getProgressDrawable();
            DrawableCompat.setTint(progress, Color.YELLOW);
            row.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        viewHolder.movie = data.get(position);
        viewHolder.title.setText(viewHolder.movie.getTitle());
        viewHolder.description.setText(viewHolder.movie.getDescription());
        viewHolder.releaseDate.setText(viewHolder.movie.getReleaseDate());
        viewHolder.genre.setText(viewHolder.movie.getGenre());
        viewHolder.thumbnail.setImageUrl(viewHolder.movie.getThumbnail(), imageLoader);
        viewHolder.ratingBar.setRating(Float.parseFloat(viewHolder.movie.getRating()));
        viewHolder.ratingBar.setFocusable(false);


        final ViewHolder description_view_holder = viewHolder;
        viewHolder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent movieDetails = new Intent(getContext(), DetailsActivity.class);
                movieDetails.putExtra("Description", description_view_holder.movie.getDescription());
                movieDetails.putExtra("Image", description_view_holder.movie.getThumbnail());
                mContext.startActivity(movieDetails);

            }
        });



        if (viewHolder.movie.getYurl() != null) {
            viewHolder.url.setText("Watch Trailer " + "Click Here");
            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), VideoActivity.class);
                    intent.putExtra("Url", finalViewHolder.movie.getYurl());
                    mContext.startActivity(intent);

                }
            });
        } else {
            viewHolder.url.setText("Trailer Coming Soon");
        }

        final ViewHolder finalViewHolder = viewHolder;

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieDetails = new Intent(getContext(), DetailsActivity.class);
                movieDetails.putExtra("Description", finalViewHolder.movie.getDescription());
                movieDetails.putExtra("Image", finalViewHolder.movie.getThumbnail());
                mContext.startActivity(movieDetails);
            }
        });


        return row;
    }


    public class ViewHolder {

        Movie movie;
        TextView title, description, releaseDate, genre;
        NetworkImageView thumbnail;
        TextView url;
        RatingBar ratingBar;


    }

}

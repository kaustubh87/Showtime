package com.kvinzanekar.kvin.showtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by kvin on 5/25/16.
 */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        Intent i = getIntent();
        String url = i.getStringExtra("Description");
        String movie_url = i.getStringExtra("Image");
        String cast_details = i.getStringExtra("CastDetails");
        TextView movieDescription = (TextView) findViewById(R.id.movie_description);
        movieDescription.setText(url);

        NetworkImageView image = (NetworkImageView)findViewById(R.id.movie_image);
        image.setImageUrl(movie_url,imageLoader);

        NetworkImageView cast = (NetworkImageView)findViewById(R.id.cast);
        cast.setImageUrl(cast_details,imageLoader);


    }

}

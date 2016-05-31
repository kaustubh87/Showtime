package com.kvinzanekar.kvin.showtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {


    private String GOOGLE_API_KEY ="AIzaSyBns4VsOw5qzvWGJyC4RnLLwvDJihtOJAI";
    private String YOUTUBE_VIDEO_ID ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(GOOGLE_API_KEY,this);



        Intent i = getIntent();
        String url = i.getStringExtra("Url");



        YOUTUBE_VIDEO_ID = getVideoId(url);


    }

    public static String getVideoId(@NonNull String url) {
        String reg = "(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find())
            return matcher.group(1);
        return null;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {


        Toast.makeText(this, "Initialized Youtube Player Successfully", Toast.LENGTH_LONG).show();
        youTubePlayer.setPlayerStateChangeListener(playerStateChangedListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        if(!wasRestored){

            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);

        }

    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

            // Toast.makeText(VideoActivity.this," Video is playing " ,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPaused() {
            // Toast.makeText(VideoActivity.this," Video is paused " ,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };

    YouTubePlayer.PlayerStateChangeListener playerStateChangedListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

            // Toast.makeText(VideoActivity.this," Click Ad now " ,Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoStarted() {

            //Toast.makeText(VideoActivity.this," Video has started " ,Toast.LENGTH_LONG).show();

        }

        @Override
        public void onVideoEnded() {

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };


    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {


        //  Toast.makeText(this,"Initialized Youtube Player Failed" ,Toast.LENGTH_LONG).show();

    }
}
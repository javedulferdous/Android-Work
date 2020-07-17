package com.example.ytplayer;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends YouTubeBaseActivity {
    private static final String TAG = "MainActivity";
    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mInitializedListener;
    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate: Starting");
        btnPlay = (Button) findViewById(R.id.btnPlay);
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);

        mInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onClick: Initialization Done.");

                youTubePlayer.loadPlaylist("PLLE2zxR_kgxJFrh0hldEELqbkoXoBLnI-");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onClick: Initialization Failed.");
            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Initializing Youtube Player.");
                mYouTubePlayerView.initialize((YTConfig.getApiKey()), mInitializedListener);
                Log.d(TAG, "onClick: Initialization Done.");
            }
        });

        /*  Accelerometer  */
        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if (tx > 1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                } else if (tx < -1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
            }
        });

        /* Gyroscope */
        gyroscope = new Gyroscope(this);
        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz)
            {
                if (rx > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
                else if (rx < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
                }
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        accelerometer.register();
        gyroscope.register();
    }
}

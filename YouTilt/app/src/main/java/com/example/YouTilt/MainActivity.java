package com.example.YouTilt;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private YouTubePlayer player;
    private Accelerometer accelerometer;
    public TextView txtView, txtview2;
    float rightValue=0;
    private SensorManager flickManager;
    private Sensor flick;
    private float[] mGravity;
    private Gyroscope gyroscope;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);
        txtView = (TextView) findViewById(R.id.ttview);
        txtview2 = (TextView) findViewById(R.id.ttview2);
        final EditText seekToText = (EditText) findViewById(R.id.seek_to_text);
        Button seekToButton = (Button) findViewById(R.id.seek_to_button);
        seekToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int skipToSecs = Integer.valueOf(seekToText.getText().toString());
                player.seekToMillis(skipToSecs * 1000);
                Log.d("dur:"+skipToSecs * 1000,"Second");
            }
        });
        /* Gyroscope */
        gyroscope = new Gyroscope(this);
        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz)
            {

                if (ry > 1.0f)
                {
                    /*getWindow().getDecorView().setBackgroundColor(Color.RED);
                    Log.d("TX Value:"+ tx,"Right");
                    rightValue = (int)tx * 10000;
                    rightValue +=player.getCurrentTimeMillis();
                    player.seekToMillis(Math.abs((rightValue)));
                    txtView.setText("Forward:"+(int)(rightValue/10000));
                    rightValue=0;*/
                        getWindow().getDecorView().setBackgroundColor(Color.RED);
                        //rightValue = tx;
                        //rightValue +=player.getCurrentTimeMillis();
                        //Log.d("TX Value:"+ tx+"RightValue:"+rightValue,"Right");
                        Log.v("Right First","Ry "+ry);

                        if(ry >1.0 &&  ry <=1.2)
                        {
                            player.seekRelativeMillis (10000);
                            txtView.setText("Forward 1.5x");
                            rightValue=0;
                            Log.v("Right","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry >1.20 &&  ry <=1.30)
                        {
                            player.seekRelativeMillis (20000);
                            txtView.setText("Forward 2x");
                            rightValue=0;
                            Log.v("Right","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;

                        }
                        else if(ry >1.30 &&  ry <=1.40)
                        {
                            player.seekRelativeMillis (30000);
                            txtView.setText("Forward 3x");
                            rightValue=0;
                            Log.v("Right","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry >1.40 && ry <=1.5)
                        {
                            player.seekRelativeMillis (40000);
                            txtView.setText("Forward 4x");
                            rightValue=0;
                            Log.v("Right","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry >1.5 &&  ry <=1.7)
                        {
                            player.seekRelativeMillis (50000);
                            txtView.setText("Forward 5x");
                            rightValue=0;
                            Log.v("Right","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry >1.7 &&  ry <=1.9)
                        {
                            player.seekRelativeMillis (60000);
                            txtView.setText("Forward 6x");
                            rightValue=0;
                            Log.v("Right","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else {
                            player.seekRelativeMillis (70000);
                            txtView.setText("Forward 7x and more");
                            rightValue=0;
                            Log.v("Right","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        //txtView.setText("Forward:"+(int)(rightValue/10000));
                        //rightValue=0;
                    }
                    else if (ry < -1.0f) {
                    /*getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    rightValue = Math.abs((int)tx * 10000);
                    rightValue -=player.getCurrentTimeMillis();
                    player.seekToMillis(Math.abs((rightValue)));
                    Log.d("TX Value:"+ tx,"Left");
                    txtView.setText("Backward:"+Math.abs((int)(rightValue/10000)));
                    rightValue=0;*/
                        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                        //rightValue = tx;
                        //rightValue +=player.getCurrentTimeMillis();
                        //Log.d("TX Value:"+ tx+"RightValue:"+rightValue,"Right");
                        Log.v("Left First","ry "+ry);

                        if(ry <-1.0 &&  ry >=-1.2)
                        {
                            player.seekRelativeMillis (-10000);
                            txtView.setText("Backward 1x");
                            rightValue=0;
                            Log.v("left","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry <-1.20 &&  ry >=-1.30)
                        {
                            player.seekRelativeMillis (-20000);
                            txtView.setText("Backward 2x");
                            rightValue=0;
                            Log.v("left","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry <-1.30 &&  ry >=-1.40)
                        {
                            player.seekRelativeMillis (-30000);
                            txtView.setText("Backward 3x");
                            rightValue=0;
                            Log.v("left","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry <-1.40 && ry >=-1.5)
                        {
                            player.seekRelativeMillis (-40000);
                            txtView.setText("Backward 4x");
                            rightValue=0;
                            Log.v("left","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry <-1.5 &&  ry >=-1.7)
                        {
                            player.seekRelativeMillis (-50000);
                            txtView.setText("Backward 5x");
                            rightValue=0;
                            Log.v("left","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else if(ry <-1.7 &&  ry >=-1.9)
                        {
                            player.seekRelativeMillis (-60000);
                            txtView.setText("Backward 6x");
                            rightValue=0;
                            Log.v("left","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                        else {
                            player.seekRelativeMillis (-70000);
                            txtView.setText("Backward 7x and more");
                            rightValue=0;
                            Log.v("left","ry "+ry);
                            txtview2.setText("Ry: " + ry);
                            rx = 0;
                            ry = 0;
                            rz = 0;
                        }
                    }
                }

        });

        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if (tx > 1.0f)
                {
                    try {
                        player.next();
                        txtView.setText("Next");
                        getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                    } catch (Exception e) {
                        txtView.setText("No video is available");
                    }
                }
                else if(tx <- 1.0f)
                {
                    try{
                        player.previous();
                        txtView.setText("Previous");
                        getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                    }
                    catch (Exception e)
                    {
                        txtView.setText("No video is available");
                    }

                }
                if (ty > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    player.pause();
                    Log.v("Left","Tx "+tx);
                    txtView.setText("Pause");

                }
                else if (ty < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
                    Log.d("TY Value:"+ ty,"DOWN");
                    player.play();
                    txtView.setText("Play");
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
        accelerometer.unregister();
        gyroscope.unregister();
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        if (!wasRestored) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("nOHkmBYuPK4");
            arrayList.add("V70ruRzEGOI");
            arrayList.add("CcsUYu0PVxY");
            arrayList.add("RrDHrwLUtvk");
            arrayList.add("79DijItQXMM");
            arrayList.add("h_TFfNp_C2c");
            arrayList.add("4xMNeY2U7ck");
            arrayList.add("16T0lvYHtkE");

            player.loadVideos(arrayList);
            //player.loadPlaylist("PLLE2zxR_kgxJEZjdz3fV-QtOWSwdEBZb5"); // Playlist: https://www.youtube.com/watch?v=QQwV3fJRZdM&list=PLLE2zxR_kgxJEZjdz3fV-QtOWSwdEBZb5
            //player.setFullscreen(true);
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}
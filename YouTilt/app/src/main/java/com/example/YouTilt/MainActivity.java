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
import android.content.Context;
import android.hardware.SensorEventListener;
import java.util.Objects;
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
    int max = 0, i =0;
    private SensorManager shakeManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    private Gyroscope gyroscope;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shakeManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Objects.requireNonNull(shakeManager).registerListener(mSensorListener, shakeManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        mAccel = 10f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

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
                        getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
                        player.seekRelativeMillis (10000);
                        txtView.setText("Forward 10 second");

                }
                    else if (ry < -1.0f) {
                        getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                        player.seekRelativeMillis(-10000);
                        txtView.setText("Backward 10 second");
                    }
                }
        });

        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if (tx > 1.0f)
                {
                    /*try {
                        player.next();
                        txtView.setText("Next");
                        getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                    } catch (Exception e) {
                        txtView.setText("No video is available");
                    }*/
                }
                else if(tx <- 1.0f)
                {
                    /*try{
                        player.previous();
                        txtView.setText("Previous");
                        getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                    }
                    catch (Exception e)
                    {
                        txtView.setText("No video is available");
                    }*/

                }
                if (ty > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    /*player.pause();
                    Log.v("Left","Tx "+tx);
                    txtView.setText("Pause");*/

                }
                else if (ty < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
                    /*Log.d("TY Value:"+ ty,"DOWN");
                    player.play();
                    txtView.setText("Play");*/
                }
            }
        });
    }
    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 12) {
                //Toast.makeText(getApplicationContext(), "Shake event detected", Toast.LENGTH_SHORT).show();
                if(player.isPlaying() == true)
                {
                    player.pause();
                    txtView.setText("Pause");
                    getWindow().getDecorView().setBackgroundColor(Color.CYAN);
                }
                else{
                    player.play();
                    txtView.setText("Play");
                    getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                }
            }
            else
            {
                //getWindow().getDecorView().setBackgroundColor(Color.GREEN);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
    @Override
    protected void onResume()
    {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
        shakeManager.registerListener(mSensorListener, shakeManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        accelerometer.unregister();
        gyroscope.unregister();
        shakeManager.unregisterListener(mSensorListener);

    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        this.player = player;
        if (!wasRestored) {
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("qiYKqAPlTdk");
            arrayList.add("AR-l4OBsPG0");
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
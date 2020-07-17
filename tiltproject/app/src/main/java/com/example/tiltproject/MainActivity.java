package com.example.tiltproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Accelerometer accelerometer;
    //private Gyroscope gyroscope;
    public TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.ttview);

        /*  Accelerometer  */
        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                if (tx > 1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    Log.d("TX Value:"+ tx,"Right");
                    txtView.setText("Right");

                } else if (tx < -1.0f) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                    Log.d("TX Value:"+ tx,"Left");
                    txtView.setText("Left");
                }
                if (ty > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    txtView.setText("Up");
                    Log.d("TY Value:"+ ty,"UP");
                }
                else if (ty < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
                    txtView.setText("Down");
                    Log.d("TY Value:"+ ty,"DOWN");
                }

            }
        });

        /* Gyroscope */
       /* gyroscope = new Gyroscope(this);
        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz)
            {
                if (rx > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                    txtView.setText("Up");

                }
                else if (rx < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.DKGRAY);
                    txtView.setText("Down");

                }
            }
        });
        */
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        accelerometer.register();
       // gyroscope.register();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        accelerometer.unregister();
       // gyroscope.unregister();
    }
}

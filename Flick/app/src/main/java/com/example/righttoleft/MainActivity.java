package com.example.righttoleft;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView xValue;
    TextView yValue;
    TextView zValue;
    private SensorManager sensorMan;
    private Sensor accelerometer;

    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xValue = (TextView)findViewById(R.id.xTextView);
        sensorMan = (SensorManager)this.getSystemService(SENSOR_SERVICE); // initialization of sensorManager
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // initialization of LINEAR_ACCELERATION
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        //sensorMan.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorMan.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorMan.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();
            // Shake detection
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float)Math.sqrt(x*x + y*y + z*z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.8f + delta;
            // Make this higher or lower according to how much
            // motion you want to detect
            if(x > 0.01){
                xValue.setText("Movement Left");
                Log.v("MainActivity","Movement"+x);
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
            }
            else if(x <-0.01)
            {
                xValue.setText("Movement Right");
                Log.v("MainActivity","Movement"+x);
                getWindow().getDecorView().setBackgroundColor(Color.RED);
            }
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // required method
    }}
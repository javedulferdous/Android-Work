package com.example.onflick;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;

    private static final int SWIPE_MIN_DISTANCE = 50;

    private static final int SWIPE_THRESHOLD_VELOCITY = 10;     //200;

    private static final int SWIPE_MAX_OFF_PATH = 1500;         //250;

    private GestureDetector mGestureDetector;

    private int flickCount = 0;
    private float mSwipeSpeedAverageX = 0.0f;
    private float mSwipeSpeedAverageY = 0.0f;
    private int mSwipeSpeedMaxX = 0;
    private int mSwipeSpeedMaxY = 0;


    private final float SLOPE_RATE = 1.2f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector(this, mOnGestureListener);
        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private final GestureDetector.SimpleOnGestureListener mOnGestureListener = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            try {

                float distance_x = Math.abs((event1.getX() - event2.getX()));
                float velocity_x = Math.abs(velocityX);
                float distance_y = Math.abs((event1.getY() - event2.getY()));
                float velocity_y = Math.abs(velocityY);
                String strX = null;
                String strY = null;

                mSwipeSpeedMaxX = Math.max(mSwipeSpeedMaxX, (int)velocity_x);
                mSwipeSpeedMaxY = Math.max(mSwipeSpeedMaxY, (int)velocity_y);

                mSwipeSpeedAverageX = (mSwipeSpeedAverageX * flickCount + velocity_x) / (flickCount + 1);
                mSwipeSpeedAverageY = (mSwipeSpeedAverageY * flickCount + velocity_y) / (flickCount + 1);
                flickCount++;
                /*textView1.setText("Horizontal  distance:" + distance_x +"\n"+ "Horizontal  speed:" + velocity_x+"\n"
                        + "Vertical  distance:" + distance_y +"\n"+ "Vertical  speed:" + velocity_y+"\n"
                        + "Flick count:" + mSwipeCount+"\n"
                         + "Horizontal maximum speed:" + mSwipeSpeedMaxX +"\n"+ "Horizontal average speed:" + mSwipeSpeedAverageX+"\n"
                        + "Maximum vertical speed:" + mSwipeSpeedMaxY +"\n"+ "Average vertical speed:" + mSwipeSpeedAverageY+"\n"
                );*/
                if ((Math.abs(event1.getY() - event2.getY()) > SWIPE_MAX_OFF_PATH)
                        || (Math.abs(event1.getX() - event2.getX()) > SWIPE_MAX_OFF_PATH)) {
                    textView2.setText("Vertical or horizontal distance is too large");
                }

                textView2.setText("");
                // The moving distance from the start position to the end position is greater than the specified value
                // X-axis movement speed is greater than the specified value
                if  (event1.getX() - event2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    textView2.setText("Right to left direction"+"\n" +"=======>");
                    strX = " left ";

                }
                // The moving distance from the end position to the start position is greater than the specified value
                // X-axis movement speed is greater than the specified value
                else if (event2.getX() - event1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    textView2.setText("Left to right direction"+"\n"+"<=======");
                    strX = " right ";
                }

                if (event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    textView2.setText(textView2.getText() + "\n"+"Bottom to the top");
                    strY = " Upper ";
                }
                else if (event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    textView2.setText(textView2.getText() + "\n"+"Top to down");
                    strY = " below";
                }


                if (distance_x > distance_y * SLOPE_RATE) {

                    textView2.setText(textView2.getText() +"\n"+ "BestDirection :" + strX + " direction ");
                } else if (distance_x * SLOPE_RATE < distance_y) {
                    textView2.setText(textView2.getText() +"\n"+ "BestDirection :" + strY + " direction");
                } else {
                    textView2.setText(textView2.getText() + "\n"+"Almost diagonal" + strX + strY+ " direction");
                }

            } catch (Exception e) {
            }

            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
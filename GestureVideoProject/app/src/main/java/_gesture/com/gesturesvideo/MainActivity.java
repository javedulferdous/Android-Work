package _gesture.com.gesturesvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
//import android.widget.MediaController;
import android.view.MotionEvent;
import android.view.View;
import android.net.Uri;
import android.widget.VideoView;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity implements
        View.OnTouchListener,
        GestureDetector.OnGestureListener,
        View.OnDragListener,
        GestureDetector.OnDoubleTapListener {


    private static final String TAG = "MainActivity";

    //widgets
    private VideoView videoView;


    //vars
    private GestureDetector mGestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector(this, this);
        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnTouchListener(this);
        //Set MediaController  to enable play, pause, forward, etc options.
        //MediaController mediaController = new MediaController(this);
       // mediaController.setAnchorView(videoView);
        //Location of Media File
        //Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/video");
        //Starting VideoView By Setting MediaController and URI
        //videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
    }

    public void startButton(View view) {
        if (videoView!= null) {
            videoView.resume();
        } else {
            videoView.start();
        }
    }
    public void pauseButton(View view) {
        if (videoView!= null && videoView.isPlaying()){
            videoView.pause();
        }
    }
    public void restartButton(View view) {
        if (videoView!= null || videoView.isPlaying()) {
            videoView.stopPlayback();
            videoView.start();
        }
    }
    public void stopButton(View view) {
        if (videoView!= null && videoView.isPlaying()) {
            videoView.stopPlayback();
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
    int action  = motionEvent.getAction();
        if (view.getId() == R.id.videoView) {
            //methods for touching video
            mGestureDetector.onTouchEvent(motionEvent);
            return true;
        }
        return true;
    }

    /*
        GestureDetector
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG, "onDown Called"+"\n"+ "position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG, "onShowPress Called "+"\n"+" position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG, "onSingleTapUp Called "+"\n"+" position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //Log.d(TAG, "onScroll Called and position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("onScroll Called "+"\n"+ "position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY());
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        //Log.d(TAG, "onLongPress Called and position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("onLongPress Called"+"\n"+" position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY());
        View.DragShadowBuilder builder = new View.DragShadowBuilder(videoView);

        videoView.startDrag(null,
                builder,
                null,
                0);

        builder.getView().setOnDragListener(this);
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        //Log.d(TAG, "onFling Called and position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("onFling Called "+"\n"+" position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY());
        return false;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {

        switch (dragEvent.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(TAG, "onDrag started Called and position: X:"+ dragEvent.getX()+", Y:"+dragEvent.getY()+")");
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(TAG, "onDrag entered Called and position: X:"+ dragEvent.getX()+", Y:"+dragEvent.getY()+")");
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                Log.d(TAG, "onDrag location Called and position: X:"+ dragEvent.getX()+", Y:"+dragEvent.getY()+")");

                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                Log.d(TAG, "onDrag exit Called and position: X:"+ dragEvent.getX()+", Y:"+dragEvent.getY()+")");
                return true;

            case DragEvent.ACTION_DROP:
                Log.d(TAG, "onDrag drop Called and position: X:"+ dragEvent.getX()+", Y:"+dragEvent.getY()+")");
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(TAG, "onDrag ended Called and position: X:"+ dragEvent.getX()+", Y:"+dragEvent.getY()+")");
                return true;

            // An unknown action type was received.
            default:
                Log.e(TAG, "Unknown action type received by OnStartDragListener.");
                break;

        }

        return false;
    }

    /*
        DoubleTap
     */

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        //Log.d(TAG, "onSingleTapConfirmed Called and position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("onSingleTapConfirmed Called "+"\n"+" position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY());
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        //Log.d(TAG, "onDoubleTap Called and position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("onDoubleTap Called "+"\n"+" position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY());
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        //Log.d(TAG, "onDoubleTapEvent Called and position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY()+")");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("onDoubleTapEvent Called "+"\n"+" position: X:"+ motionEvent.getX()+", Y:"+motionEvent.getY());
        return false;
    }
}






















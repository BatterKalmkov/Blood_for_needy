package com.example.bobby.blooddonation;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class WhyDonate extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    ImageSwitcher imageSwitcher;
    ImageButton ib1,ib2;
    int[] imageArray1 = { R.drawable.blooddonate, R.drawable.whydonate2,
            R.drawable.whydonate4, R.drawable.whydonate5,
            R.drawable.whydonate3};
    int i = 0;
    Animation in,out;
    GestureDetectorCompat gc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_why_donate);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher1);
        ib1=(ImageButton)findViewById(R.id.imageButton10);
        ib2=(ImageButton)findViewById(R.id.imageButton11);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_XY);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });

        gc=new GestureDetectorCompat(this,this);
        gc.setOnDoubleTapListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        out=AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        imageSwitcher.setImageResource(R.drawable.blooddonate);

        imageSwitcher.postDelayed(new Runnable() {
            @Override
            public void run() {
                ib2.setVisibility(View.INVISIBLE);
                ib1.setVisibility(View.INVISIBLE);
            }
        },3000);

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setInAnimation(in);
                if (i >=imageArray1.length-1 )
                {
                    i=-1;
                }
                    i++;
                imageSwitcher.setImageResource(imageArray1[i]);

            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setInAnimation(out);
                if(i==0)
                {
                    i=imageArray1.length;
                }
                i--;
                imageSwitcher.setImageResource(imageArray1[i]);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gc.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        ib1.setVisibility(View.VISIBLE);
        ib2.setVisibility(View.VISIBLE);
        imageSwitcher.postDelayed(new Runnable() {
            @Override
            public void run() {
                ib1.setVisibility(View.INVISIBLE);
                ib2.setVisibility(View.INVISIBLE);
            }
        },5000);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}

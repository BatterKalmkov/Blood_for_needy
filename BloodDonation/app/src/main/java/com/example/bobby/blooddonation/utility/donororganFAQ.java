package com.example.bobby.blooddonation.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.bobby.blooddonation.R;
import com.theappguruz.imagezoom.ImageViewTouch;

public class donororganFAQ extends AppCompatActivity  implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    ImageView  iv;
    ImageButton ib1,ib2;
    int[] imageArray1 = {R.drawable.faq2,
            R.drawable.faq3, R.drawable.faq4,
            R.drawable.faq5,R.drawable.faq1};
    int i = 0;
    Animation in,out;
    GestureDetectorCompat gc;
    private ImageViewTouch ivLargeImage;
    private Bitmap myBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donororgan_faq);

        iv=(ImageView) findViewById(R.id.imageView26);
        ib1=(ImageButton)findViewById(R.id.imageButton10);
        ib2=(ImageButton)findViewById(R.id.imageButton11);
        gc=new GestureDetectorCompat(this,this);
        gc.setOnDoubleTapListener(this);

        ivLargeImage = (ImageViewTouch) findViewById(R.id.ivLargeImageView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        in = AnimationUtils.loadAnimation(this,android.R.anim.slide_in_left);
        out=AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        iv.setImageResource(R.drawable.faq2);
        myBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.faq2);
        ivLargeImage.setImageBitmapReset(myBitmap, 0, true);

        iv.postDelayed(new Runnable() {
            @Override
            public void run() {
                ib2.setVisibility(View.INVISIBLE);
                ib1.setVisibility(View.INVISIBLE);
            }
        },3000);

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setAnimation(in);
                if (i >=imageArray1.length-1 )
                {
                    i=-1;
                }
                i++;
                iv.setImageResource(imageArray1[i]);
                myBitmap = BitmapFactory.decodeResource(getResources(),imageArray1[i]);
                ivLargeImage.setImageBitmapReset(myBitmap, 0, true);
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv.setAnimation(out);
                if(i==0)
                {
                    i=imageArray1.length;
                }
                i--;
                iv.setImageResource(imageArray1[i]);
                myBitmap = BitmapFactory.decodeResource(getResources(),imageArray1[i]);
                ivLargeImage.setImageBitmapReset(myBitmap, 0, true);
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
        iv.postDelayed(new Runnable() {
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


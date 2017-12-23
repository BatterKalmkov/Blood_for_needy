package com.example.bobby.blooddonation;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.bobby.blooddonation.utility.TransitionHelper;
import com.example.bobby.blooddonation.utility.donateorgan;

public class gethappiness extends AppCompatActivity {
ImageView iv1,iv2,iv3,iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gethappiness);

        iv1=(ImageView)findViewById(R.id.imageView19);
        iv2=(ImageView)findViewById(R.id.imageView20);
        iv3=(ImageView)findViewById(R.id.imageView21);
        iv4=(ImageView) findViewById(R.id.imageView25);

        Animation animation = null;
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_top);
        animation.setDuration(3000);
        iv1.startAnimation(animation);
        animation = null;
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_up_in);
        animation.setDuration(3000);
        iv3.startAnimation(animation);
        animation=null;
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right_in);
        animation.setDuration(2000);
        iv2.startAnimation(animation);
        animation=null;
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_right_in);
        animation.setDuration(2000);
        iv4.startAnimation(animation);
    }

    @Override
    protected void onResume() {
        super.onResume();

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(gethappiness.this,donateblood.class);
                startActivity(intent);
                finish();
            }
        });


        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(gethappiness.this,payumoneyintegration.class);
                startActivity(intent);
                finish();

            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(gethappiness.this,volunteer.class);
                startActivity(intent);
                finish();
            }
        });

        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(gethappiness.this,donateorgan.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

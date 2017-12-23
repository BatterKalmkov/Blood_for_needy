package com.example.bobby.blooddonation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bobby.blooddonation.activity.LoginActivity;

public class chat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent=new Intent(chat.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

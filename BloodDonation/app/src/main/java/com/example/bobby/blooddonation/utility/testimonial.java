package com.example.bobby.blooddonation.utility;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bobby.blooddonation.R;
import com.example.bobby.blooddonation.Testimonial;
import com.example.bobby.blooddonation.WhyDonate;


public class testimonial extends Fragment {

    public testimonial() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.testimonial, container, false);
        Intent i=new Intent(getContext(),Testimonial.class);
        startActivity(i);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }}

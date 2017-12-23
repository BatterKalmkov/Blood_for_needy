package com.example.bobby.blooddonation;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.bobby.blooddonation.adapter.testimonialadapter;
import com.example.bobby.blooddonation.utility.DividerItemDecoration;
import com.example.bobby.blooddonation.utility.testimonialutility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Testimonial extends AppCompatActivity {
    private List<testimonialutility> testimonialList = new ArrayList<>();
    private RecyclerView recyclerView;
    private testimonialadapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimonial);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Testimonial.this, writetestimonial.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new testimonialadapter(testimonialList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        new JSONP().execute();
    }

    class JSONP extends AsyncTask<Void,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            testimonialList.clear();
            jsonurl="http://sixthsenseit.com/blood/gettestimonials.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            String data="";
            try {
                URL url=new URL(jsonurl);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder  stringBuilder=new StringBuilder();
                while((jsonstring= bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(jsonstring+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONObject jsonRootObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonRootObject.optJSONArray("server_response");
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String email = jsonObject.optString("email").toString();
                    String date = jsonObject.optString("date").toString();
                    String district = jsonObject.optString("district").toString();
                    String state = jsonObject.optString("state").toString();
                    String testimonial = jsonObject.optString("testimonial").toString();
                    String pic=jsonObject.optString("pic").toString();
                    String name=jsonObject.optString("name").toString();
                    testimonialutility testimonial1 = new testimonialutility(pic,testimonial,email,state,date,district,name);
                    testimonialList.add(testimonial1);
                }
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return  data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mAdapter.notifyDataSetChanged();
        }
    }
}

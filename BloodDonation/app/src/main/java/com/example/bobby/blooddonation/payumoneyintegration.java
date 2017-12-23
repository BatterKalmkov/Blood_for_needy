package com.example.bobby.blooddonation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bobby.blooddonation.utility.DatabaseHelper;
import com.example.bobby.blooddonation.utility.FbDatabaseHelper;
import com.example.bobby.blooddonation.utility.GlDatabaseHelper;
import com.example.bobby.blooddonation.utility.TwDatabaseHelper;
import com.example.bobby.blooddonation.utility.testimonialutility;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;

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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class payumoneyintegration extends AppCompatActivity {
    EditText rechargeAmt;
    Button Paynow;
    String getFname,getPhone,getEmail,getCity,getState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payumoneyintegration);

        rechargeAmt  = (EditText)findViewById(R.id.rechargeAmt);
        Paynow       = (Button)findViewById(R.id.Paynow);


        if(SplashScreen1.logout==true)
        {
            new JSONP().execute();
        }
        else if(SplashScreen1.fblogout==true)
        {
            new fbJSONP().execute();
        }
        else if(SplashScreen1.twlogout==true)
        {
            new twJSONP().execute();
        }
        else if(SplashScreen1.gllogout==true)
        {
            new glJSONP().execute();
        }

        Paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getAmt   = rechargeAmt.getText().toString().trim();
                 if(Integer.parseInt(getAmt)<10)
                 {
                     Toast.makeText(payumoneyintegration.this, "Please enter amount greater than or equal to 10", Toast.LENGTH_SHORT).show();
                 }
                else
                 {
                     Intent intent = new Intent(getApplicationContext(), PayMentGateWay.class);
                     intent.putExtra("FIRST_NAME", getFname);
                     intent.putExtra("PHONE_NUMBER", getPhone);
                     intent.putExtra("EMAIL_ADDRESS", getEmail);
                     intent.putExtra("RECHARGE_AMT", getAmt);
                     intent.putExtra("CITY", getCity);
                     intent.putExtra("STATE", getState);
                     startActivity(intent);
                     finish();
                 }
            }
        });
    }


    class fbJSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;
        String fbid=" ",fbname=" ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://sixthsenseit.com/blood/fetchfbsignin.php";

            FbDatabaseHelper dh1=new FbDatabaseHelper(payumoneyintegration.this);
            Cursor b11 = dh1.getalldata();
            if (b11.getCount() > 0) {
                while (b11.moveToNext()) {
                    fbid = b11.getString(2);
                    fbname = b11.getString(3);
                }
            }
            while(fbid.equalsIgnoreCase(" "))
            {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("fbname") + "=" + URLEncoder.encode(fbname) + "&" + URLEncoder.encode("fbid") + "=" + URLEncoder.encode(fbid);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONObject jsonRootObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonRootObject.optJSONArray("server_response");
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = null;

                    jsonObject = jsonArray.getJSONObject(i);
                    getEmail = jsonObject.optString("email").toString();
                    getCity = jsonObject.optString("district").toString();
                    getState = jsonObject.optString("state").toString();
                    getFname=jsonObject.optString("name").toString();
                    getPhone=jsonObject.optString("mobile").toString();
                }

                data = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    class twJSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;
        String twid=" ",twname=" ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jsonurl = "http://sixthsenseit.com/blood/fetchtwsignin.php";
            TwDatabaseHelper dh2=new TwDatabaseHelper(payumoneyintegration.this);
            Cursor b12 = dh2.getalldata();
            if (b12.getCount() > 0) {
                while (b12.moveToNext()) {
                    twid = b12.getString(2);
                    twname = b12.getString(3);
                }
            }
            while(twid.equalsIgnoreCase(" "))
            {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("twname") + "=" + URLEncoder.encode(twname) + "&" + URLEncoder.encode("twid") + "=" + URLEncoder.encode(String.valueOf(twid));

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONObject jsonRootObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonRootObject.optJSONArray("server_response");
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = null;

                    jsonObject = jsonArray.getJSONObject(i);
                    getEmail = jsonObject.optString("email").toString();
                    getCity = jsonObject.optString("district").toString();
                    getState = jsonObject.optString("state").toString();
                    getFname=jsonObject.optString("name").toString();
                    getPhone=jsonObject.optString("mobile").toString();
                }

                data = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

    class glJSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;
        String googleid=" ",googlename=" ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            jsonurl = "http://sixthsenseit.com/blood/fetchglsignin.php";
            GlDatabaseHelper dh3=new GlDatabaseHelper(payumoneyintegration.this);
            Cursor b13 = dh3.getalldata();
            if (b13.getCount() > 0) {
                while (b13.moveToNext()) {
                    googleid = b13.getString(2);
                    googlename = b13.getString(3);
                }
            }
            while(googleid.equalsIgnoreCase(" "))
            {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("glname") + "=" + URLEncoder.encode(googlename) + "&" + URLEncoder.encode("glid") + "=" + URLEncoder.encode(String.valueOf(googleid));
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONObject jsonRootObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonRootObject.optJSONArray("server_response");
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = null;
                    jsonObject = jsonArray.getJSONObject(i);
                    getEmail = jsonObject.optString("email").toString();
                    getCity = jsonObject.optString("district").toString();
                    getState = jsonObject.optString("state").toString();
                    getFname=jsonObject.optString("name").toString();
                    getPhone=jsonObject.optString("mobile").toString();
                }

                data = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }

    class JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;
        String username=" ";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://sixthsenseit.com/blood/fetchsignin.php";

            DatabaseHelper dh=new DatabaseHelper(payumoneyintegration.this);
            Cursor b10 = dh.getalldata();
            if (b10.getCount() > 0) {
                while (b10.moveToNext()) {
                    username= b10.getString(1);
                }
            }

            while(username.equalsIgnoreCase(" "))
            {
                try {
                    wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username);
                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                JSONObject jsonRootObject = new JSONObject(stringBuilder.toString());
                JSONArray jsonArray = jsonRootObject.optJSONArray("server_response");
                for(int i=0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = null;

                    jsonObject = jsonArray.getJSONObject(i);
                    getEmail = jsonObject.optString("email").toString();
                    getCity = jsonObject.optString("district").toString();
                    getState = jsonObject.optString("state").toString();
                    getFname=jsonObject.optString("name").toString();
                    getPhone=jsonObject.optString("mobile").toString();
                }

                data = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}
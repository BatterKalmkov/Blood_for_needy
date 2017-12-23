package com.example.bobby.blooddonation;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bobby.blooddonation.utility.DatabaseHelper;
import com.example.bobby.blooddonation.utility.FbDatabaseHelper;
import com.example.bobby.blooddonation.utility.GlDatabaseHelper;
import com.example.bobby.blooddonation.utility.TwDatabaseHelper;

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

public class writetestimonial extends AppCompatActivity {
    ImageView iv;
    EditText e1,e2,e4;
    Button b1,b2;
    String email ,state, district, pic, testimonial,name;
    Bitmap bitmap,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writetestimonial);

        iv=(ImageView)findViewById(R.id.imageView17);
        e1=(EditText)findViewById(R.id.editText11);
        e2=(EditText)findViewById(R.id.editText12);;
        e4=(EditText)findViewById(R.id.editText14);

        b1=(Button)findViewById(R.id.button6);
        b2=(Button)findViewById(R.id.button7);
    }

    @Override
    protected void onResume() {
        super.onResume();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = e1.getText().toString();
                name = e2.getText().toString();
                testimonial = e4.getText().toString();

                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty() || e4.getText().toString().isEmpty())
                {
                    Toast.makeText(writetestimonial.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if(!email.contains("@")  || !email.contains(".") )
                {
                    Toast.makeText(writetestimonial.this, "Please check your email-id.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   new JSONP().execute();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(writetestimonial.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(SplashScreen1.logout==true)
        {
            DatabaseHelper dh=new DatabaseHelper(this);
            Cursor b11=dh.getalldata();
            while (b11.moveToNext())
            {
             name=b11.getString(1);
            }

            new JSONP2().execute();
        }
        else if(SplashScreen1.fblogout==true)
        {
            FbDatabaseHelper dh=new FbDatabaseHelper(this);
            Cursor b12=dh.getalldata();
            while (b12.moveToNext())
            {
                email=b12.getString(5);
                name=b12.getString(3);
                state="";
                district="";
                pic=b12.getString(4);
                new LongOperation(pic).execute();
            }
        }
        else if(SplashScreen1.twlogout==true)
        {
            TwDatabaseHelper dh=new TwDatabaseHelper(this);
            Cursor b13=dh.getalldata();
            while (b13.moveToNext())
            {
                email="";
                state="";
                district="";
                name=b13.getString(3);;
                pic=b13.getString(4);
                new LongOperation(pic).execute();
            }
        }
        else if(SplashScreen1.gllogout==true)
        {
            GlDatabaseHelper dh=new GlDatabaseHelper(this);
            Cursor b14=dh.getalldata();
            while (b14.moveToNext())
            {
                email=b14.getString(5);
                state="";
                district="";
                name=b14.getString(3);;
                pic=b14.getString(4);
                new LongOperation(pic).execute();
            }
        }
        e1.setText(email);
        e2.setText(name);
    }

    public Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private class LongOperation extends AsyncTask<Void, Void,Void> {
        String url;
        LongOperation(String url)
        {
            this.url=url;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                image = DownloadImage(url);
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            iv.setImageBitmap(image);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private Bitmap DownloadImage(String url) throws InterruptedException {
        URL imageURL = null;

        try {
            imageURL = new URL(url);
        }

        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        InputStream inputStream=null;
        try {

            HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
            connection.setDoInput(true);
            connection.connect();
            inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        while(bitmap==null)
        {
            bitmap = BitmapFactory.decodeStream(inputStream);
            wait(100);
        }
        return bitmap;
    }

    public class  JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://sixthsenseit.com/blood/writetestimonial.php";
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
                String postdata = URLEncoder.encode("email") + "=" + URLEncoder.encode(email) + "&" + URLEncoder.encode("state") + "=" + URLEncoder.encode(state)
                 + "&" +URLEncoder.encode("district") + "=" + URLEncoder.encode(district) + "&" + URLEncoder.encode("testimonial") + "=" + URLEncoder.encode(testimonial)
                + "&" +URLEncoder.encode("pic") + "=" + URLEncoder.encode(pic)+ "&" +URLEncoder.encode("name") + "=" + URLEncoder.encode(name);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
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

                data=stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
                Toast.makeText(writetestimonial.this,s, Toast.LENGTH_SHORT).show();
                e1.setText("");
                e2.setText("");
                e4.setText("");
            Intent intent=new Intent(writetestimonial.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public class  JSONP2 extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://sixthsenseit.com/blood/getalldata.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try
            {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(name);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
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
                    email=jsonObject.optString("email").toString();
                    state = jsonObject.optString("state").toString();
                    district=jsonObject.optString("district").toString();
                    pic= jsonObject.optString("pic").toString();
                    name=jsonObject.optString("name").toString();
                }

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

            iv.setImageBitmap(decodeBase64(pic));
            e1.setText(email);
            e2.setText(name);
        }
    }
}

package com.example.bobby.blooddonation;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.bobby.blooddonation.utility.DatabaseHelper;
import com.example.bobby.blooddonation.utility.FbDatabaseHelper;
import com.example.bobby.blooddonation.utility.GlDatabaseHelper;
import com.example.bobby.blooddonation.utility.TwDatabaseHelper;

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


public class SplashScreen1 extends AppCompatActivity {
    ImageView iv;
    RadioButton r1,r2,r3,r4,r5;
    Button b1;

    int[] imageArray = { R.drawable.blooddonation, R.drawable.image5,
            R.drawable.image2, R.drawable.image3,
            R.drawable.benefits};

    DatabaseHelper dh;
    TwDatabaseHelper dh2;
    FbDatabaseHelper dh1;
    GlDatabaseHelper dh3;
    public static Boolean logout,fblogout,twlogout,gllogout;
    public String usernamesignin,passwordsignin;

    String jsonurl,jsonstring;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen1);
        iv =(ImageView) findViewById(R.id.imageView);
        r1=(RadioButton)findViewById(R.id.radioButton);
        r2=(RadioButton)findViewById(R.id.radioButton2);
        r3=(RadioButton)findViewById(R.id.radioButton3);
        r4=(RadioButton)findViewById(R.id.radioButton4);
        r5=(RadioButton)findViewById(R.id.radioButton5);
        b1=(Button)findViewById(R.id.button);

        dh = new DatabaseHelper(SplashScreen1.this);
        dh1 = new FbDatabaseHelper(SplashScreen1.this);
        dh2 = new TwDatabaseHelper(SplashScreen1.this);
        dh3=new GlDatabaseHelper(SplashScreen1.this);

        new LongOperation().execute();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(SplashScreen1.this,Signin.class );
                startActivity(intent);
                finish();
                handler.removeCallbacks(runnable);
            }
        });
    }

    private class LongOperation extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://sixthsenseit.com/blood/signin.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            Cursor b10 = dh.getalldata();
            if (b10.getCount() == 0) {
                logout = false;
            }
            else
            {
                while (b10.moveToNext())
                {
                    usernamesignin = b10.getString(1);
                    passwordsignin = b10.getString(2);
                    if (b10.getString(3).equalsIgnoreCase("True"))
                    {
                        logout = true;

                        try {
                            URL url = new URL(jsonurl);
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                            httpURLConnection.setRequestMethod("POST");
                            OutputStream outputStream = httpURLConnection.getOutputStream();
                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                            String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(usernamesignin) + "&" + URLEncoder.encode("password") + "=" + URLEncoder.encode(passwordsignin);

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
                    }
                    else
                    {
                        logout = false;
                    }
                }
            }

            Cursor b11 = dh1.getalldata();
            if (b11.getCount() == 0) {
                fblogout = false;
            } else {
                while (b11.moveToNext()) {
                    if (b11.getString(1).equalsIgnoreCase("True")) {
                        data="logged";
                        fblogout = true;
                    }
                    else
                    {
                        fblogout = false;
                    }
                }
            }

            Cursor b12 = dh2.getalldata();
            if (b12.getCount() == 0) {
                twlogout = false;
            } else {
                while (b12.moveToNext()) {;
                    if (b12.getString(1).equalsIgnoreCase("True")) {
                        data="logged";
                        twlogout = true;
                    }
                    else
                    {
                        twlogout = false;
                    }
                }
            }

            Cursor b13 = dh3.getalldata();
            if (b13.getCount() == 0) {
                gllogout = false;
            } else {
                while (b13.moveToNext()) {;
                    if (b13.getString(1).equalsIgnoreCase("True")) {
                        data="logged";
                        gllogout = true;
                    }
                    else
                    {
                        gllogout = false;
                    }
                }
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.contains("logged"))
            {
                Intent bj=new Intent(SplashScreen1.this,MainActivity.class);
                bj.putExtra("username",usernamesignin);
                startActivity(bj);
                finish();
            }
            else
            {
                handler = new Handler();
                runnable = new Runnable()
                {
                 int i = 0;

                public void run()
                {
                    if (i > imageArray.length-1 )
                    {
                        i = 0;
                        Intent intent=new Intent(SplashScreen1.this,Signin.class );
                        startActivity(intent);
                        finish();
                        return;
                    }

                    iv.setImageResource(imageArray[i]);
                    i++;

                    if(i==1)
                    {
                        r1.setChecked(true);
                    }
                    else if(r1.isChecked())
                    {
                        r2.setChecked(true);
                        r1.setChecked(false);
                        b1.setVisibility(View.VISIBLE);
                    }
                    else if(r2.isChecked())
                    {
                        r3.setChecked(true);
                        r2.setChecked(false);
                    }
                    else if(r3.isChecked())
                    {
                        r4.setChecked(true);
                        r3.setChecked(false);
                    }
                    else if(r4.isChecked())
                    {
                        r5.setChecked(true);
                        r4.setChecked(false);
                    }
                    handler.postDelayed(this, 1500+(i*500));
                }
            };
                handler.postDelayed(runnable, 100);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}

package com.example.bobby.blooddonation.utility;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobby.blooddonation.R;
import com.example.bobby.blooddonation.SplashScreen1;
import com.example.bobby.blooddonation.gethappiness;

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

public class donateorgan extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1, s2, s3, s4, s5, s6;
    Button b1;
    String gender = " ", age = " ", ethnicity = " ", donateorgan = " ", referral = " ",smsalert=" ";
    AlertDialog b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donateorgan);

        s1 = (Spinner) findViewById(R.id.spinner13);
        s2 = (Spinner) findViewById(R.id.spinner14);
        s3 = (Spinner) findViewById(R.id.spinner15);
        s4 = (Spinner) findViewById(R.id.spinner16);
        s5 = (Spinner) findViewById(R.id.spinner17);
        s6 = (Spinner) findViewById(R.id.spinner20);

        b1 = (Button) findViewById(R.id.Submit);

        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        s3.setOnItemSelectedListener(this);
        s4.setOnItemSelectedListener(this);
        s5.setOnItemSelectedListener(this);
        s6.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Select Gender-");
        categories.add("Male");
        categories.add("Female");

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Select Age-");
        for (int i = 18; i <= 100; i++) {
            categories2.add(String.valueOf(i));
        }

        List<String> categories3 = new ArrayList<String>();
        categories3.add("Select Ethnicity-");
        categories3.add("Asian-Indian,Pakistani,Bangladeshi");
        categories3.add("Arab");
        categories3.add("Gypsy or Irish Traveller");
        categories3.add("Asian-Other");
        categories3.add("Caribbean");
        categories3.add("African");
        categories3.add("Other");
        categories3.add("White");
        categories3.add("Not Repored");
        categories3.add("Mixed");

        List<String> categories4 = new ArrayList<String>();
        categories4.add("All-");
        categories4.add("Some Organs");
        categories4.add("Corneas (eyes)");
        categories4.add("Kidneys");
        categories4.add("Heart");
        categories4.add("Lungs");
        categories4.add("Liver");
        categories4.add("Pancreas");

        List<String> categories5 = new ArrayList<String>();
        categories5.add("Select Referral-");
        categories5.add("Playstore");
        categories5.add("Internet Search");
        categories5.add("Email");
        categories5.add("Facebook");
        categories5.add("Twitter");
        categories5.add("Word of Mouth from Friends/Family");

        List<String> categories6 = new ArrayList<String>();
        categories6.add("Yes");
        categories6.add("No");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories4);
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories5);
        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories6);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(dataAdapter);
        s2.setAdapter(dataAdapter2);
        s3.setAdapter(dataAdapter3);
        s4.setAdapter(dataAdapter4);
        s5.setAdapter(dataAdapter5);
        s6.setAdapter(dataAdapter6);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.toString().contains("spinner13")) {
            gender = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner14")) {
            age = parent.getItemAtPosition(position).toString();
        } else if (parent.toString().contains("spinner15")) {
            ethnicity = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner16")) {
            donateorgan = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner17")) {
            referral = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner20")) {
            smsalert = parent.getItemAtPosition(position).toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gender.equalsIgnoreCase("Select Gender-") || age.equalsIgnoreCase("Select Age-") || ethnicity.equalsIgnoreCase("Select Ethnicity-") || referral.equalsIgnoreCase("Select Referral-"))
                {
                    Toast.makeText(donateorgan.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else {
                    new JSONP().execute();
                }
            }
        });

        SpannableString ss = new SpannableString("By submitting this form, I accept the Terms and Conditions and give consent to register my intent to donate any suitable organs and tissue for transplantation.*For more details related tp organ donation, please go through FAQ section");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                View v = getLayoutInflater().inflate(R.layout.organdonortermsandcondition, null);

                AlertDialog.Builder b2 = new AlertDialog.Builder(donateorgan.this)
                        .setCancelable(true)
                        .setView(v);

                b3 = b2.create();
                b3.show();

                Button b10 = (Button) v.findViewById(R.id.button7);

                b10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        b3.cancel();
                    }
                });

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.RED);
            }
        };


        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
               Intent intent=new Intent(donateorgan.this,donororganFAQ.class);
                startActivity(intent);

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.RED);
            }
        };


        ss.setSpan(clickableSpan2,221,225,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan, 38,59, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = (TextView) findViewById(R.id.textView35);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    class JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;
        String id,type;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://bjprogrammer.co.nf/blooddonation/organdonationregister.php";

            if(SplashScreen1.fblogout==true) {
                FbDatabaseHelper dh1 = new FbDatabaseHelper(donateorgan.this);
                Cursor b11 = dh1.getalldata();
                if (b11.getCount() > 0) {
                    while (b11.moveToNext()) {
                        id = b11.getString(2);
                        type = "fb";
                    }
                }
            }
            else if(SplashScreen1.twlogout==true) {
                TwDatabaseHelper dh2 = new TwDatabaseHelper(donateorgan.this);
                Cursor b12 = dh2.getalldata();
                if (b12.getCount() > 0) {
                    while (b12.moveToNext()) {
                        id = b12.getString(2);
                        type = "tw";
                    }
                }
            }
            else if(SplashScreen1.gllogout==true) {
                GlDatabaseHelper dh3 = new GlDatabaseHelper(donateorgan.this);
                Cursor b13 = dh3.getalldata();
                if (b13.getCount() > 0) {
                    while (b13.moveToNext()) {
                        id = b13.getString(2);
                        type = "gl";
                    }
                }
            }
            else if(SplashScreen1.logout==true) {
                DatabaseHelper dh = new DatabaseHelper(donateorgan.this);
                Cursor b10 = dh.getalldata();
                if (b10.getCount() > 0) {
                    while (b10.moveToNext()) {
                        id = b10.getString(1);
                        type = "registered";
                    }
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
                String postdata = URLEncoder.encode("gender") + "=" + URLEncoder.encode(gender) + "&" + URLEncoder.encode("age") + "=" + URLEncoder.encode(age)
                        + "&" + URLEncoder.encode("ethnicity") + "=" + URLEncoder.encode(ethnicity)+ "&" + URLEncoder.encode("donateorgan") + "=" + URLEncoder.encode(donateorgan)
                        + "&" + URLEncoder.encode("referral") + "=" + URLEncoder.encode(referral)+ "&" + URLEncoder.encode("smsalert") + "=" + URLEncoder.encode(smsalert)
                        + "&" + URLEncoder.encode("id") + "=" + URLEncoder.encode(id)+ "&" + URLEncoder.encode("type") + "=" + URLEncoder.encode(type);


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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.contains("Thank"))
            {
                Toast.makeText(donateorgan.this,s, Toast.LENGTH_SHORT).show();
                Intent bj=new Intent(donateorgan.this,gethappiness.class);
                startActivity(bj);
                finish();
            }
            else
            {
                Toast.makeText(donateorgan.this,s, Toast.LENGTH_SHORT).show();
            }
        }
    }

}

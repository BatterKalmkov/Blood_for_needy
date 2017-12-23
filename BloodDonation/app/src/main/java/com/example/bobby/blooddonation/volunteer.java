package com.example.bobby.blooddonation;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

public class volunteer extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner s1, s2, s3, s4, s5, s6, s7;
    Button b1;
    String gender = " ", age = " ", areaofinterest = " ", volunteeringtype = " ", time = " ", place = " ", referral = " ";
    AlertDialog b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer);

        s1 = (Spinner) findViewById(R.id.spinner3);
        s2 = (Spinner) findViewById(R.id.spinner4);
        s3 = (Spinner) findViewById(R.id.spinner5);
        s4 = (Spinner) findViewById(R.id.spinner6);
        s5 = (Spinner) findViewById(R.id.spinner7);
        s6 = (Spinner) findViewById(R.id.spinner8);
        s7 = (Spinner) findViewById(R.id.spinner9);

        b1 = (Button) findViewById(R.id.Submit);

        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        s3.setOnItemSelectedListener(this);
        s4.setOnItemSelectedListener(this);
        s5.setOnItemSelectedListener(this);
        s6.setOnItemSelectedListener(this);
        s7.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Select Gender-");
        categories.add("Male");
        categories.add("Female");

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Select Age-");
        for (int i = 18; i <= 80; i++) {
            categories2.add(String.valueOf(i));
        }

        List<String> categories3 = new ArrayList<String>();
        categories3.add("Select Area of Interest-");
        categories3.add("Campaign");
        categories3.add("Event Management");
        categories3.add("Organisational Networking");
        categories3.add("Media Management");
        categories3.add("Data Entry/Filling");
        categories3.add("Organisational Surveys");
        categories3.add("Training and Capacity Building");
        categories3.add("Communication & Outreach");
        categories3.add("Fund Raising");
        categories3.add("Graphic Design");
        categories3.add("Writing Proposals");
        categories3.add("Publication Design");
        categories3.add("Fine Art/Illustration");
        categories3.add("Legal & Technical Advice (for professionals only)");
        categories3.add("Social Media/Blogging");

        List<String> categories4 = new ArrayList<String>();
        categories4.add("Select Volunteering Type-");
        categories4.add("Regular Office Volunteering");
        categories4.add("Fieldwork Oriented Volunteering");
        categories4.add("Home Based/ Online Work");
        categories4.add("Event Based Volunteering");

        List<String> categories5 = new ArrayList<String>();
        categories5.add("Select Time-");
        categories5.add("1 day");
        categories5.add("2 days");
        categories5.add("3 days");
        categories5.add("4 days");
        categories5.add("5 days");
        categories5.add("Weekends");

        List<String> categories6 = new ArrayList<String>();
        categories6.add("Select Place-");
        categories6.add("Local");
        categories6.add("Local + Outstation(Within India)");

        List<String> categories7 = new ArrayList<String>();
        categories7.add("Select from following-");
        categories7.add("Website");
        categories7.add("Playstore");
        categories7.add("Facebook");
        categories7.add("Twitter");
        categories7.add("Email Referral");
        categories7.add("Advertisements");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories3);
        ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories4);
        ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories5);
        ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories6);
        ArrayAdapter<String> dataAdapter7 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories7);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(dataAdapter);
        s2.setAdapter(dataAdapter2);
        s3.setAdapter(dataAdapter3);
        s4.setAdapter(dataAdapter4);
        s5.setAdapter(dataAdapter5);
        s6.setAdapter(dataAdapter6);
        s7.setAdapter(dataAdapter7);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if (parent.toString().contains("spinner3")) {
            gender = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner4")) {
            age = parent.getItemAtPosition(position).toString();
        } else if (parent.toString().contains("spinner5")) {
            areaofinterest = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner6")) {
            volunteeringtype = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner7")) {
            time = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner8")) {
            place = parent.getItemAtPosition(position).toString();

        } else if (parent.toString().contains("spinner9")) {
            referral = parent.getItemAtPosition(position).toString();

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
                if (gender.equalsIgnoreCase("Select Gender-") || age.equalsIgnoreCase("Select Age-") || areaofinterest.equalsIgnoreCase("Select Area of Interest-") || volunteeringtype.equalsIgnoreCase("Select Volunteering Type-") || time.equalsIgnoreCase("Select Time-") || place.equalsIgnoreCase("Select Place-") || referral.equalsIgnoreCase("Select from following-")) {
                    Toast.makeText(volunteer.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    new JSONP().execute();
                }
            }
        });

        SpannableString ss = new SpannableString("By submitting this form, I accept the Terms and Conditions and consent to contact me for volunteering purpose");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                View v = getLayoutInflater().inflate(R.layout.volunteertermsandcondition, null);

                AlertDialog.Builder b2 = new AlertDialog.Builder(volunteer.this)
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

        ss.setSpan(clickableSpan, 38,59, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = (TextView) findViewById(R.id.textView25);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    class JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;
        String id,type;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://sixthsenseit.com/blood/volunteerregister.php";

            if(SplashScreen1.fblogout==true) {
                FbDatabaseHelper dh1 = new FbDatabaseHelper(volunteer.this);
                Cursor b11 = dh1.getalldata();
                if (b11.getCount() > 0) {
                    while (b11.moveToNext()) {
                        id = b11.getString(2);
                        type = "fb";
                    }
                }
            }
            else if(SplashScreen1.twlogout==true) {
                TwDatabaseHelper dh2 = new TwDatabaseHelper(volunteer.this);
                Cursor b12 = dh2.getalldata();
                if (b12.getCount() > 0) {
                    while (b12.moveToNext()) {
                        id = b12.getString(2);
                        type = "tw";
                    }
                }
            }
            else if(SplashScreen1.gllogout==true) {
                GlDatabaseHelper dh3 = new GlDatabaseHelper(volunteer.this);
                Cursor b13 = dh3.getalldata();
                if (b13.getCount() > 0) {
                    while (b13.moveToNext()) {
                        id = b13.getString(2);
                        type = "gl";
                    }
                }
            }
            else if(SplashScreen1.logout==true) {
                DatabaseHelper dh = new DatabaseHelper(volunteer.this);
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
                        + "&" + URLEncoder.encode("volunteeringtype") + "=" + URLEncoder.encode(volunteeringtype)+ "&" + URLEncoder.encode("time") + "=" + URLEncoder.encode(time)
                        + "&" + URLEncoder.encode("place") + "=" + URLEncoder.encode(place)+ "&" + URLEncoder.encode("referral") + "=" + URLEncoder.encode(referral)
                        + "&" + URLEncoder.encode("areaofinterest") + "=" + URLEncoder.encode(areaofinterest)+ "&" + URLEncoder.encode("id") + "=" + URLEncoder.encode(id)
                        + "&" + URLEncoder.encode("type") + "=" + URLEncoder.encode(type);

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
                Toast.makeText(volunteer.this,s, Toast.LENGTH_SHORT).show();
                Intent bj=new Intent(volunteer.this,gethappiness.class);
                startActivity(bj);
                finish();
            }
            else
            {
                Toast.makeText(volunteer.this,s, Toast.LENGTH_SHORT).show();
            }
        }
    }

}

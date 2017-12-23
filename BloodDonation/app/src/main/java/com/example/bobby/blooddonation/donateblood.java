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
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class donateblood extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner s1,s2,s3,s4,s5,s6,s7,s8;
    Button b1;
    String age,gender,weight,donor,dd,mm,yy,lastdonation,smsalert;
    AlertDialog b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donateblood);

        s1=(Spinner)findViewById(R.id.spinner13);
        s2=(Spinner)findViewById(R.id.spinner14);
        s3=(Spinner)findViewById(R.id.spinner15);
        s4=(Spinner)findViewById(R.id.spinner16);
        s5=(Spinner)findViewById(R.id.spinner17);
        s6=(Spinner)findViewById(R.id.spinner18);
        s7=(Spinner)findViewById(R.id.spinner19);
        s8=(Spinner)findViewById(R.id.spinner20);

        b1=(Button)findViewById(R.id.Submit);

        s1.setOnItemSelectedListener(this);
        s2.setOnItemSelectedListener(this);
        s3.setOnItemSelectedListener(this);
        s4.setOnItemSelectedListener(this);
        s5.setOnItemSelectedListener(this);
        s6.setOnItemSelectedListener(this);
        s7.setOnItemSelectedListener(this);
        s8.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Select Gender-");
        categories.add("Male");
        categories.add("Female");

        List<String> categories2 = new ArrayList<String>();
        categories2.add("Select Age-");
        for(int i=18;i<=55;i++) {
            categories2.add(String.valueOf(i));
        }

        List<String> categories3 = new ArrayList<String>();
        categories3.add("Select Weight(in Kgs)-");
        for(int i=50;i<=135;i++) {
            categories3.add(String.valueOf(i));
        }

        List<String> categories4 = new ArrayList<String>();
        categories4.add("Yet to Donate");
        categories4.add("Regular Donor");
        categories4.add("On Need Basis");

        List<String> categories5 = new ArrayList<String>();
        categories5.add("DD");
        categories5.add("-");
        for(int i=1;i<=30;i++) {
            categories5.add(String.valueOf(i));
        }

        List<String> categories6 = new ArrayList<String>();
        categories6.add("MM");
        categories6.add("-");
        for(int i=1;i<=12;i++) {
            categories6.add(String.valueOf(i));
        }

        List<String> categories7 = new ArrayList<String>();
        categories7.add("YYYY");
        categories7.add("-");
        Calendar c = Calendar.getInstance();

        for(long i = c.get(Calendar.YEAR); i>=1977; i--) {
            categories7.add(String.valueOf(i));
        }

        List<String> categories8 = new ArrayList<String>();
        categories8.add("Yes");
        categories8.add("No");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter <String> dataAdapter2 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories2);
        ArrayAdapter <String> dataAdapter3 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories3);
        ArrayAdapter <String> dataAdapter4 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories4);
        ArrayAdapter <String> dataAdapter5 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories5);
        ArrayAdapter <String> dataAdapter6 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories6);
        ArrayAdapter <String> dataAdapter7 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories7);
        ArrayAdapter <String> dataAdapter8 = new ArrayAdapter <String>(this, android.R.layout.simple_spinner_item, categories8);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s1.setAdapter(dataAdapter);
        s2.setAdapter(dataAdapter2);
        s3.setAdapter(dataAdapter3);
        s4.setAdapter(dataAdapter4);
        s5.setAdapter(dataAdapter5);
        s6.setAdapter(dataAdapter6);
        s7.setAdapter(dataAdapter7);
        s8.setAdapter(dataAdapter8);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.toString().contains("spinner13")) {
            gender = parent.getItemAtPosition(position).toString();

        }
        else if(parent.toString().contains("spinner14"))
        {
            age= parent.getItemAtPosition(position).toString();
        }
        else if(parent.toString().contains("spinner15"))
        {
            weight= parent.getItemAtPosition(position).toString();

        }
        else if(parent.toString().contains("spinner16"))
        {
            donor= parent.getItemAtPosition(position).toString();

        }
        else if(parent.toString().contains("spinner17"))
        {
            dd= parent.getItemAtPosition(position).toString();

        }
        else if(parent.toString().contains("spinner18"))
        {
            mm= parent.getItemAtPosition(position).toString();

        }
        else if(parent.toString().contains("spinner19"))
        {
            yy= parent.getItemAtPosition(position).toString();

        }
        else if(parent.toString().contains("spinner20"))
        {
            smsalert= parent.getItemAtPosition(position).toString();

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
                if(gender.equalsIgnoreCase("Select Gender-") || age.equalsIgnoreCase("Select Age-") || weight.equalsIgnoreCase("Select Weight(in Kgs)-") || dd.equalsIgnoreCase("DD") || mm.equalsIgnoreCase("MM") || yy.equalsIgnoreCase("YYYY"))
                {
                    Toast.makeText(donateblood.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    lastdonation=dd+"/"+mm+"/"+yy;
                    new JSONP().execute();
                }
            }
        });

        SpannableString ss = new SpannableString("By submitting this form, I accept the Terms and Conditions and consent to have my contact and donor information published to the potential blood recipients.I have read the Eligibility Criteria and confirm that I am eligible to donate blood.");

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                View v = getLayoutInflater().inflate(R.layout.termsandconditions, null);

                AlertDialog.Builder b2 = new AlertDialog.Builder(donateblood.this)
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

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                View v = getLayoutInflater().inflate(R.layout.donateblood, null);

                AlertDialog.Builder b2 = new AlertDialog.Builder(donateblood.this)
                        .setCancelable(true)
                        .setView(v);

               final  AlertDialog b3 = b2.create();
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


        ss.setSpan(clickableSpan,171,193,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan2,38,59,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = (TextView) findViewById(R.id.textView35);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        //textView.setHighlightColor(Color.TRANSPARENT);
    }

    class JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;
        String id,type;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://sixthsenseit.com/blood/donorregister.php";

            if(SplashScreen1.fblogout==true) {
                FbDatabaseHelper dh1 = new FbDatabaseHelper(donateblood.this);
                Cursor b11 = dh1.getalldata();
                if (b11.getCount() > 0) {
                    while (b11.moveToNext()) {
                        id = b11.getString(2);
                        type = "fb";
                    }
                }
            }
            else if(SplashScreen1.twlogout==true) {
                TwDatabaseHelper dh2 = new TwDatabaseHelper(donateblood.this);
                Cursor b12 = dh2.getalldata();
                if (b12.getCount() > 0) {
                    while (b12.moveToNext()) {
                        id = b12.getString(2);
                        type = "tw";
                    }
                }
            }
            else if(SplashScreen1.gllogout==true) {
                GlDatabaseHelper dh3 = new GlDatabaseHelper(donateblood.this);
                Cursor b13 = dh3.getalldata();
                if (b13.getCount() > 0) {
                    while (b13.moveToNext()) {
                        id = b13.getString(2);
                        type = "gl";
                    }
                }
            }
            else if(SplashScreen1.logout==true) {
                DatabaseHelper dh = new DatabaseHelper(donateblood.this);
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
                        + "&" + URLEncoder.encode("weight") + "=" + URLEncoder.encode(weight)+ "&" + URLEncoder.encode("lastdonation") + "=" + URLEncoder.encode(lastdonation)
                        + "&" + URLEncoder.encode("donortype") + "=" + URLEncoder.encode(donor)+ "&" + URLEncoder.encode("smsalert") + "=" + URLEncoder.encode(smsalert)
                        + "&" + URLEncoder.encode("id") + "=" + URLEncoder.encode(id) + "&" + URLEncoder.encode("type") + "=" + URLEncoder.encode(type);

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
                Toast.makeText(donateblood.this,s, Toast.LENGTH_SHORT).show();
                Intent bj=new Intent(donateblood.this,gethappiness.class);
                startActivity(bj);
                finish();
            }
            else
            {
                Toast.makeText(donateblood.this,s, Toast.LENGTH_SHORT).show();
            }
        }
    }

}

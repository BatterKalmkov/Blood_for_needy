package com.example.bobby.blooddonation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.bobby.blooddonation.adapter.blogAdapter;
import com.example.bobby.blooddonation.adapter.eventAdapter;
import com.example.bobby.blooddonation.utility.CalendarView;
import com.example.bobby.blooddonation.utility.DividerItemDecoration;
import com.example.bobby.blooddonation.utility.blogutility;
import com.example.bobby.blooddonation.utility.eventutility;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class event extends AppCompatActivity {
    HashSet<Date> events;
    Date d;
    SimpleDateFormat dateformat;
    CalendarView cv;

    public static List<eventutility> eventList;
    private RecyclerView recyclerView;
    private eventAdapter mAdapter3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        events = new HashSet<>();
        d = new Date();
        dateformat = new SimpleDateFormat("dd/MM/yyyy");
        cv = ((CalendarView) findViewById(R.id.calendar_view));

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayPress(Date date)  {

                int i=0;
                Date d=new Date();
                for(i=0;i< event.eventList.size();i++)
                {

                    try {
                      d=  dateformat.parse(event.eventList.get(i).getDate());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (date.getDate() == d.getDate() &&
                            date.getMonth() == d.getMonth() &&
                            date.getYear() == d.getYear())
                    {
                        break;
                    }
                }
                Intent intent = new Intent(event.this, playevent.class);
                intent.putExtra("position3", i);
                Toast.makeText(event.this,event.eventList.get(i).getEvent() , Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        eventList = new ArrayList<>();
        mAdapter3 = new eventAdapter(eventList);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(event.this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter3);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(event.this, recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                eventutility event = eventList.get(position);
                Toast.makeText(event.this, event.getEvent() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(event.this, playevent.class);
                i.putExtra("position3", position);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        new JSONP().execute();

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    class JSONP extends AsyncTask<Void,Void,String>
    {
        String jsonurl,jsonstring;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            eventList.clear();
            jsonurl="http://sixthsenseit.com/blood/events.php";
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
                    String time = jsonObject.optString("time").toString();
                    String venue = jsonObject.optString("venue").toString();
                    String date = jsonObject.optString("date").toString();
                    String description = jsonObject.optString("description").toString();
                    String event = jsonObject.optString("event").toString();
                    String contactperson = jsonObject.optString("contactperson").toString();
                    String mobile = jsonObject.optString("mobile").toString();
                    String latitude=jsonObject.optString("latitude").toString();
                    String longitude=jsonObject.optString("longitude").toString();
                    eventutility event1 = new eventutility(venue, time,contactperson,event,date,description,mobile,latitude,longitude);
                    eventList.add(event1);

                    try
                    {
                        d = dateformat.parse(date);
                    }
                    catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
                    events.add(d);
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

            mAdapter3.notifyDataSetChanged();
            cv.updateCalendar(events);
        }
    }
}


package com.example.bobby.blooddonation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.bobby.blooddonation.adapter.blogAdapter;
import com.example.bobby.blooddonation.utility.DividerItemDecoration;
import com.example.bobby.blooddonation.utility.blogutility;

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
import java.util.ArrayList;
import java.util.List;

public class blog extends AppCompatActivity {
    public static List<blogutility> blogList;
    private RecyclerView recyclerView3;
    private blogAdapter mAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        blogList = new ArrayList<>();
        mAdapter3 = new blogAdapter(blogList);
        recyclerView3 = (RecyclerView) findViewById(R.id.recycler_view3);
        recyclerView3.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView3.setLayoutManager(mLayoutManager);
        recyclerView3.addItemDecoration(new DividerItemDecoration(blog.this, LinearLayoutManager.VERTICAL));
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setAdapter(mAdapter3);

        recyclerView3.addOnItemTouchListener(new RecyclerTouchListener(blog.this, recyclerView3, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                blogutility blog = blogList.get(position);
                Toast.makeText(blog.this, blog.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(blog.this, playblog.class);
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

            blogList.clear();
            jsonurl="http://sixthsenseit.com/blood/blog.php";
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
                    String title = jsonObject.optString("title").toString();
                    String source = jsonObject.optString("source").toString();
                    String content = jsonObject.optString("content").toString();
                    String pic = jsonObject.optString("pic").toString();
                    blogutility blog = new blogutility(title, source, time,content,pic);
                    blogList.add(blog);
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
        }
    }
}

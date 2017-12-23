package com.example.bobby.blooddonation.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bobby.blooddonation.R;
import com.example.bobby.blooddonation.utility.testimonialutility;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class testimonialadapter extends RecyclerView.Adapter<testimonialadapter.MyViewHolder> {
    ImageView iv;
    Bitmap bitmap,image;

    private List<testimonialutility> testimonialList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, testimonial, date,district,state;


        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            testimonial = (TextView) view.findViewById(R.id.testimonial);
            date = (TextView) view.findViewById(R.id.Date);
            district=(TextView)view.findViewById(R.id.district);
            state=(TextView)view.findViewById(R.id.state);
            iv=(ImageView)view.findViewById(R.id.imageView18);
        }
    }


    public testimonialadapter(List<testimonialutility> testimonialList) {
        this.testimonialList = testimonialList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.testimonial_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        testimonialutility movie = testimonialList.get(position);
        holder.name.setText(movie.getName());
        holder.district.setText(movie.getDistrict());
        holder.state.setText(movie.getState());
        holder.date.setText(movie.getDate());
        holder.testimonial.setText(movie.getTestimonial());
        new LongOperation(iv).execute(movie.getPic());
    }

    @Override
    public int getItemCount() {
        return testimonialList.size();
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

    private class LongOperation extends AsyncTask<String, Void,Bitmap> {
        ImageView img;

        LongOperation(ImageView img)
        {
            this.img=img;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            if(!params[0].contains("http"))
            {
                image= decodeBase64(params[0]);
            }
            else
            {
                try
                {
                    image = DownloadImage(params[0]);
                } catch (Exception e)
                {

                }
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            super.onPostExecute(s);

            img.setImageBitmap(s);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        public  Bitmap decodeBase64(String input)
        {
            byte[] decodedBytes = Base64.decode(input, 0);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        }
    }
}

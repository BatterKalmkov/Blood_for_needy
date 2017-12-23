package com.example.bobby.blooddonation.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bobby.blooddonation.R;
import com.example.bobby.blooddonation.utility.blogutility;
import com.example.bobby.blooddonation.utility.timelineutility;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class timelineAdapter extends RecyclerView.Adapter<timelineAdapter.MyViewHolder> implements Animation.AnimationListener{
    public  List<timelineutility> timelineList;
    Context context;
    Animation animAnticipateOvershoot;

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation==animAnticipateOvershoot && getItemCount()>0)
        {
            remove(timelineList.get(0));
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,time,source,content,bloodgroup;

        public MyViewHolder(View view)
        {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            source = (TextView) view.findViewById(R.id.venue);
            time = (TextView) view.findViewById(R.id.time);
            content=(TextView)view.findViewById(R.id.content);
            bloodgroup=(TextView)view.findViewById(R.id.bloodgroup);

            context=view.getContext();
        }
    }

    public timelineAdapter(List<timelineutility> timelineList) {
        this.timelineList = timelineList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timeline_list_row, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        timelineutility blog = timelineList.get(position);
        holder.title.setText(blog.getSource());
        holder.source.setText(blog.getDistrict()+","+blog.getState());
        holder.time.setText(blog.getTime());
        holder.content.setText("Message-"+blog.getContent());
        holder.bloodgroup.setText(blog.getBloodgroup());

        if(position==0 && getItemCount()>0) {
            //animate(holder);
        }
    }

    @Override
    public int getItemCount() {
        return timelineList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView on a predefined position
    public void insert(int position, timelineutility data) {
        timelineList.add(position, data);
        notifyItemInserted(position);
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(timelineutility data) {
        int position = timelineList.indexOf(data);
        timelineList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,getItemCount());
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.custom_animation);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

}


package com.example.bobby.blooddonation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bobby.blooddonation.R;
import com.example.bobby.blooddonation.utility.eventutility;

import java.util.List;


public class eventAdapter extends RecyclerView.Adapter<eventAdapter.MyViewHolder> {
    public  List<eventutility> eventList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView event,venue,date,time,contactperson,description,mobile;

        public MyViewHolder(View view)
        {
            super(view);
            event = (TextView) view.findViewById(R.id.title);
            venue = (TextView) view.findViewById(R.id.venue);
            time = (TextView) view.findViewById(R.id.time);
            date=(TextView)view.findViewById(R.id.date);
        }
    }

    public eventAdapter(List<eventutility> eventList) {
        this.eventList = eventList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        eventutility event= eventList.get(position);
        holder.venue.setText(event.getVenue());
        holder.event.setText(event.getEvent());
        holder.time.setText(event.getTime());
        holder.date.setText(event.getDate());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }
}


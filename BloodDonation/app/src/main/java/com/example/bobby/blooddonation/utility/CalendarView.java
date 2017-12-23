package com.example.bobby.blooddonation.utility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bobby.blooddonation.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class CalendarView extends LinearLayout
{
	private static final int DAYS_COUNT = 42;
	private static final String DATE_FORMAT = "MMM yyyy";
	private String dateFormat;
	private Calendar currentDate = Calendar.getInstance();
	private EventHandler eventHandler = null;
	private LinearLayout header;
	private ImageView btnPrev;
	private ImageView btnNext;
	private TextView txtDate;
	private GridView grid;
    public static HashSet<Date> eventDays;

	int[] monthSeason = new int[] {2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};

	public CalendarView(Context context)
	{
		super(context);
	}

	public CalendarView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initControl(context, attrs);
	}

	public CalendarView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initControl(context, attrs);
	}

	private void initControl(Context context, AttributeSet attrs)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.control_calendar, this);

		loadDateFormat(attrs);
		assignUiElements();
		assignClickHandlers();
		updateCalendar();
	}

	private void loadDateFormat(AttributeSet attrs)
	{
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

		try
		{
			dateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
			if (dateFormat == null)
				dateFormat = DATE_FORMAT;
		}
		finally
		{
			ta.recycle();
		}
	}
	private void assignUiElements()
	{
		// layout is inflated, assign local variables to components
		header = (LinearLayout)findViewById(R.id.calendar_header);
		btnPrev = (ImageView)findViewById(R.id.calendar_prev_button);
		btnNext = (ImageView)findViewById(R.id.calendar_next_button);
		txtDate = (TextView)findViewById(R.id.calendar_date_display);
		grid = (GridView)findViewById(R.id.calendar_grid);
	}

	private void assignClickHandlers()
	{
		// add one month and refresh UI
		btnNext.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				currentDate.add(Calendar.MONTH, 1);
				updateCalendar(eventDays);
			}
		});

		// subtract one month and refresh UI
		btnPrev.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				currentDate.add(Calendar.MONTH, -1);
				updateCalendar(eventDays);
			}
		});

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> view, View cell, int position, long id)
            {

                Date d=(Date)view.getItemAtPosition(position);
                if (eventDays != null)
                {
                    int c=0;
                    for (Date eventDate : eventDays)
                    {
                        if (eventDate.getDate() == d.getDate() &&
                                eventDate.getMonth() == d.getMonth() &&
                                eventDate.getYear() == d.getYear())
                        {
                            eventHandler.onDayPress((Date)view.getItemAtPosition(position));
                            c++;
                            break;
                        }
                    }
                    if(c==0) {
                        Toast.makeText(getContext(), "No event", Toast.LENGTH_SHORT).show();
                    }
                    c=0;
                }

            }
        });
	}

	public void updateCalendar()
	{
		updateCalendar(null);
	}
	public void updateCalendar(HashSet<Date> events)
	{
		ArrayList<Date> cells = new ArrayList<>();
		Calendar calendar = (Calendar)currentDate.clone();

		// determine the cell for current month's beginning
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		// move calendar backwards to the beginning of the week
		calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

		// fill cells
		while (cells.size() < DAYS_COUNT)
		{
			cells.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}

		// update grid
		grid.setAdapter(new CalendarAdapter(getContext(), cells, events));

		// update title
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		txtDate.setText(sdf.format(currentDate.getTime()));

		// set header color according to current season
		int month = currentDate.get(Calendar.MONTH);
		int season = monthSeason[month];
	}


	private class CalendarAdapter extends ArrayAdapter<Date>
	{
		// for view inflation
		private LayoutInflater inflater;

		public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
		{
			super(context, R.layout.control_calendar_day, days);
            CalendarView.eventDays = eventDays;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View view, ViewGroup parent)
		{
			// day in question
			Date date = getItem(position);
			int day = date.getDate();
			int month = date.getMonth();
			int year = date.getYear();

			// today
			Date today = new Date();

			// inflate item if it does not exist yet
			if (view == null)
				view = inflater.inflate(R.layout.control_calendar_day, parent, false);

			// if this day has an event, specify event image
			view.setBackgroundResource(0);


			// clear styling
			((TextView)view).setTypeface(null, Typeface.NORMAL);
			((TextView)view).setTextColor(Color.BLACK);

			if (month != today.getMonth() || year != today.getYear())
			{
				// if this day is outside current month, grey it out
				((TextView)view).setTextColor(getResources().getColor(R.color.greyed_out));
			}
			else if (day == today.getDate())
			{
				// if it is today, set it to blue/bold
				((TextView)view).setTypeface(null, Typeface.BOLD);
				((TextView)view).setTextColor(getResources().getColor(R.color.today));
			}

            if (eventDays != null)
            {
                for (Date eventDate : eventDays)
                {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year)
                    {
                        // mark this day for event
                        ((TextView)view).setTypeface(null, Typeface.BOLD);
                        ((TextView)view).setTextColor(getResources().getColor(R.color.events));
                        break;
                    }
                }
            }
			// set text
			((TextView)view).setText(String.valueOf(date.getDate()));

			return view;
		}
	}

	public void setEventHandler(EventHandler eventHandler)
	{
		this.eventHandler = eventHandler;
	}

	public interface EventHandler
	{
		void onDayPress(Date date);
	}
}

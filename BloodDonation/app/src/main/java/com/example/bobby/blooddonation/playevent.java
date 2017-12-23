package com.example.bobby.blooddonation;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class playevent extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6;
    ImageView bj;
    int position3;

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playevent);

        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.textView10);
        t3 = (TextView) findViewById(R.id.textView11);
        t4 = (TextView) findViewById(R.id.textView12);
        t5 = (TextView) findViewById(R.id.textView13);
        t6 = (TextView) findViewById(R.id.textView15);

        bj = (ImageView) findViewById(R.id.imageView13);

        position3 = getIntent().getIntExtra("position3", 0);

        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        t1.setText(event.eventList.get(position3).getEvent());
        t2.setText("Date-" + event.eventList.get(position3).getDate());
        t3.setText("Time-" + event.eventList.get(position3).getTime());
        t4.setText("Venue-" + event.eventList.get(position3).getVenue());
        t5.setText("Description-\n" + event.eventList.get(position3).getDescription());
        t6.setText("For more information,please contact-" + event.eventList.get(position3).getContactperson() + " Telephone:" + event.eventList.get(position3).getMobile());

        initilizeMap();

        double latitude = Double.parseDouble(event.eventList.get(position3).getLatitude());
        double longitude = Double.parseDouble(event.eventList.get(position3).getLongitude());

        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude,longitude)).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title(event.eventList.get(position3).getEvent());
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        googleMap.addMarker(marker);

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.setMyLocationEnabled(true);

        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setTiltGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}

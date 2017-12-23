package com.example.bobby.blooddonation.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.bobby.blooddonation.R;
import com.example.bobby.blooddonation.adapter.ChatRoomsAdapter2;
import com.example.bobby.blooddonation.app.Config;
import com.example.bobby.blooddonation.app.EndPoints;
import com.example.bobby.blooddonation.app.MyApplication;
import com.example.bobby.blooddonation.GcmIntentService;
import com.example.bobby.blooddonation.gcm.NotificationUtils;
import com.example.bobby.blooddonation.helper.SimpleDividerItemDecoration;
import com.example.bobby.blooddonation.model.ChatRoom2;
import com.example.bobby.blooddonation.model.Message;
import com.example.bobby.blooddonation.model.Message2;
import com.example.bobby.blooddonation.model.User;
import com.example.bobby.blooddonation.search;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class availabledonors extends AppCompatActivity {

    private String TAG = availabledonors.class.getSimpleName();
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private ArrayList<ChatRoom2> chatRoomArrayList;
    private ChatRoomsAdapter2 mAdapter;
    private RecyclerView recyclerView;
    AlertDialog b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (MyApplication.getInstance2().getPrefManager2().getUser2() == null) {
            launchLoginActivity();
        }

        setContentView(R.layout.activity_activitymain);


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    subscribeToGlobalTopic();

                } else if (intent.getAction().equals(Config.SENT_TOKEN_TO_SERVER)) {
                    // gcm registration id is stored in our server's MySQL
                    Log.e(TAG, "GCM registration id is sent to our server");

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION2)) {
                    // new push notification is received
                    handlePushNotification(intent);
                }
            }
        };

        chatRoomArrayList = new ArrayList<>();
        mAdapter = new ChatRoomsAdapter2(this, chatRoomArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new ChatRoomsAdapter2.RecyclerTouchListener(getApplicationContext(), recyclerView, new ChatRoomsAdapter2.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // when chat is clicked, launch full chat thread activity
                ChatRoom2 chatRoom = chatRoomArrayList.get(position);
                Intent intent = new Intent(availabledonors.this, UserActivity.class);
                intent.putExtra("chat_room_id", chatRoom.getId());
                intent.putExtra("name", chatRoom.getName());
                intent.putExtra("userid",MyApplication.getInstance2().getPrefManager2().getUser2().getId());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        if (checkPlayServices()) {
            registerGCM();
            fetchChatRooms();
        }
    }

    private void handlePushNotification(Intent intent) {
        int type = intent.getIntExtra("type", -1);

        if (type == Config.PUSH_TYPE_CHATROOM) {


        } else if (type == Config.PUSH_TYPE_USER) {
            Message2 message = (Message2) intent.getSerializableExtra("message");
            String chatRoomId = intent.getStringExtra("userid");

            if (message != null && chatRoomId != null) {
                updateRow(chatRoomId, message);
            }
        }
    }

    private void updateRow(String chatRoomId, Message2 message) {
        for (ChatRoom2 cr : chatRoomArrayList)
        {
            Toast.makeText(availabledonors.this, cr.getId(), Toast.LENGTH_SHORT).show();
            if (cr.getId().equals(chatRoomId))
            {
                int index = chatRoomArrayList.indexOf(cr);
                cr.setLastMessage(message.getMessage());
                cr.setUnreadCount(cr.getUnreadCount() + 1);
                chatRoomArrayList.remove(index);
                chatRoomArrayList.add(index, cr);
                break;
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void fetchChatRooms() {
        StringRequest strReq = new StringRequest(Request.Method.POST,
                EndPoints.USERS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "response: " + response);

                try {
                    JSONObject obj = new JSONObject(response);

                    // check for error flag
                    if (obj.getBoolean("error") == false) {
                        JSONArray chatRoomsArray = obj.getJSONArray("chat_rooms");
                        for (int i = 0; i < chatRoomsArray.length(); i++) {
                            JSONObject chatRoomsObj = (JSONObject) chatRoomsArray.get(i);
                            ChatRoom2 cr = new ChatRoom2();
                            cr.setId(chatRoomsObj.getString("chat_room_id"));
                            cr.setName(chatRoomsObj.getString("name"));
                            cr.setLastMessage("");
                            cr.setUnreadCount(0);
                            cr.setTimestamp(chatRoomsObj.getString("created_at"));

                            chatRoomArrayList.add(cr);
                        }

                    } else {
                        // error in fetching chat rooms
                        Toast.makeText(getApplicationContext(), "" + obj.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "json parsing error: " + e.getMessage());
                    Toast.makeText(getApplicationContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                mAdapter.notifyDataSetChanged();

                // subscribing to all chat room topics
                subscribeToAllTopics();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse networkResponse = error.networkResponse;
                Log.e(TAG, "Volley error: " + error.getMessage() + ", code: " + networkResponse);
                Toast.makeText(getApplicationContext(), "Volley error: " + error.getMessage(), Toast.LENGTH_SHORT).show();

            }}) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("bloodgroup", search.bloodgroup);
                    params.put("state",search.item);
                    params.put("district",search.district);
                    params.put("user_id",MyApplication.getInstance2().getPrefManager2().getUser2().getId());
                    Log.e(TAG, "Params: " + params.toString());

                    return params;
                }
            };

        MyApplication.getInstance2().addToRequestQueue2(strReq);
    }

    // subscribing to global topic
    private void subscribeToGlobalTopic() {
        Intent intent = new Intent(this, GcmIntentService.class);
        intent.putExtra(GcmIntentService.KEY2, GcmIntentService.SUBSCRIBE2);
        intent.putExtra(GcmIntentService.TOPIC2, Config.TOPIC_GLOBAL);
        startService(intent);
    }

    private void subscribeToAllTopics() {
        for (ChatRoom2 cr : chatRoomArrayList) {

            Intent intent = new Intent(this, GcmIntentService.class);
            intent.putExtra(GcmIntentService.KEY2, GcmIntentService.SUBSCRIBE2);
            intent.putExtra(GcmIntentService.TOPIC2, "topic_" + cr.getId());
            startService(intent);
        }
    }

    private void launchLoginActivity() {
        Intent intent = new Intent(com.example.bobby.blooddonation.activity.availabledonors.this, LoginActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION2));

        // clearing the notification tray
        NotificationUtils.clearNotifications();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    // starting the service to register with GCM
    private void registerGCM() {
        Intent intent = new Intent(this, GcmIntentService.class);
        intent.putExtra("key2", "register");
        startService(intent);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported. Google Play Services not installed!");
                Toast.makeText(getApplicationContext(), "This device is not supported. Google Play Services not installed!", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
       final EditText e1;

        switch (menuItem.getItemId())
        {
            case R.id.action_logout:
                MyApplication.getInstance2().logout2();
                Intent intent=new Intent(getApplicationContext(),com.example.bobby.blooddonation.MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        return super.onOptionsItemSelected(menuItem);
    }
}

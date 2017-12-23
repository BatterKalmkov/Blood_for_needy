
package com.example.bobby.blooddonation.gcm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.bobby.blooddonation.activity.ChatRoomActivity;
import com.example.bobby.blooddonation.activity.MainActivity;
import com.example.bobby.blooddonation.activity.UserActivity;
import com.example.bobby.blooddonation.app.Config;
import com.example.bobby.blooddonation.app.MyApplication;
import com.example.bobby.blooddonation.model.Message;
import com.example.bobby.blooddonation.model.Message2;
import com.example.bobby.blooddonation.model.User;
import com.example.bobby.blooddonation.model.User2;
import com.google.android.gms.gcm.GcmListenerService;

import org.json.JSONException;
import org.json.JSONObject;

public class MyGcmPushReceiver extends GcmListenerService {

    private static final String TAG = MyGcmPushReceiver.class.getSimpleName();

    private NotificationUtils notificationUtils;
    private NotificationUtils2 notificationUtils2;

    @Override
    public void onMessageReceived(String from, Bundle bundle) {

        try {
            String title = bundle.getString("title");
            Boolean isBackground = Boolean.valueOf(bundle.getString("is_background"));
            String flag = bundle.getString("flag");
            String data = bundle.getString("data");
            Log.d(TAG, "From: " + from);
            Log.d(TAG, "title: " + title);
            Log.d(TAG, "isBackground: " + isBackground);
            Log.d(TAG, "flag: " + flag);
            Log.d(TAG, "data: " + data);

            if (flag == null)
                return;

            if (MyApplication.getInstance().getPrefManager().getUser() == null && MyApplication.getInstance2().getPrefManager2().getUser2() == null) {
                // user is not logged in, skipping push notification
                Log.e(TAG, "user is not logged in, skipping push notification");
                return;
            }

            if (from.startsWith("/topics/")) {
                // message received from some topic.
            }

            else {
                // normal downstream message.
            }

            switch (Integer.parseInt(flag)) {
                case Config.PUSH_TYPE_CHATROOM:
                    processChatRoomPush(title, isBackground, data);
                    break;

                case Config.PUSH_TYPE_USER:
                        processUserMessage(title, isBackground, data);
                    break;
            }
        }
        catch(Exception e)
        {
            Log.d(TAG, e.toString());
        }
    }

    private void processChatRoomPush(String title, boolean isBackground, String data) {
        if (!isBackground)
        {
            try {
                JSONObject datObj = new JSONObject(data);

                String chatRoomId = datObj.getString("chat_room_id");
                Log.d("Emergency request", chatRoomId);
                JSONObject mObj = datObj.getJSONObject("message");
                Message message = new Message();
                message.setMessage(mObj.getString("message"));
                message.setId(mObj.getString("message_id"));
                message.setCreatedAt(mObj.getString("created_at"));

                JSONObject uObj = datObj.getJSONObject("user");

                User user = new User();
                user.setId(uObj.getString("user_id"));
                user.setEmail(uObj.getString("email"));
                user.setName(uObj.getString("name"));
                message.setUser(user);

                if (uObj.getString("user_id").equals(MyApplication.getInstance2().getPrefManager2().getUser2().getId())) {
                    Log.e(TAG, "Skipping the push message as it belongs to same user");
                    return;
                }

                if(chatRoomId.equalsIgnoreCase("all"))
                {
                    Log.d("Emergency request", chatRoomId);
                    Intent resultIntent = new Intent(getApplicationContext(), com.example.bobby.blooddonation.timeline.class);
                    resultIntent.putExtra("chat_room_id", chatRoomId);
                    showNotificationMessage(getApplicationContext(), title, uObj.getString("name") + " : " + message.getMessage(), message.getCreatedAt(), resultIntent);
                    return;
                }
                // skip the message if the message belongs to same user as
                // the user would be having the same message when he was sending
                // but it might differs in your scenario

                // verifying whether the app is in background or foreground
                if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {

                    // app is in foreground, broadcast the push message
                    Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
                    pushNotification.putExtra("type", Config.PUSH_TYPE_CHATROOM);
                    pushNotification.putExtra("message", message);
                    pushNotification.putExtra("chat_room_id", chatRoomId);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                    // play notification sound
                    NotificationUtils notificationUtils = new NotificationUtils();
                    notificationUtils.playNotificationSound();
                }
                else
                {
                    // app is in background. show the message in notification try
                    Intent resultIntent = new Intent(getApplicationContext(), ChatRoomActivity.class);
                    resultIntent.putExtra("chat_room_id", chatRoomId);
                    resultIntent.putExtra("name","Blood Donation app-Group Chat");
                    showNotificationMessage(getApplicationContext(), title, user.getName() + " : " + message.getMessage(), message.getCreatedAt(), resultIntent);
                }

            } catch (JSONException e) {
                Log.e(TAG, "json parsing error: " + e.getMessage());
                Toast.makeText(getApplicationContext(), "Json parse error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Processing user specific push message
     * It will be displayed with / without image in push notification tray
     * */
    private void processUserMessage(String title, boolean isBackground, String data) {
        if (!isBackground) {

            try {
                JSONObject datObj = new JSONObject(data);


                String imageUrl = datObj.getString("image");

                JSONObject mObj = datObj.getJSONObject("message");
                Message2 message = new Message2();
                String chatRoomId = mObj.getString("chat_room_id");
                message.setMessage(mObj.getString("message"));
                message.setId(mObj.getString("message_id"));
                message.setCreatedAt(mObj.getString("created_at"));

                JSONObject uObj = datObj.getJSONObject("user");
                User2 user = new User2();
                String userID=uObj.getString("user_id");
                user.setId(uObj.getString("user_id"));
                user.setEmail(uObj.getString("email"));
                user.setName(uObj.getString("name"));
                message.setUser(user);

                if (uObj.getString("user_id").equals(MyApplication.getInstance2().getPrefManager2().getUser2().getId())) {
                    Log.e(TAG, "Skipping the push message as it belongs to same user");
                    return;
                }

                // verifying whether the app is in background or foreground
                if (!NotificationUtils2.isAppIsInBackground2(getApplicationContext())) {

                    // app is in foreground, broadcast the push message
                    Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION2);
                    pushNotification.putExtra("type", Config.PUSH_TYPE_USER);
                    pushNotification.putExtra("message", message);
                    pushNotification.putExtra("chat_room_id", chatRoomId);
                    pushNotification.putExtra("userid",userID);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                    // play notification sound
                    NotificationUtils2 notificationUtils2 = new NotificationUtils2();
                    notificationUtils2.playNotificationSound2();
                } else {

                    // app is in background. show the message in notification try
                    Intent resultIntent = new Intent(getApplicationContext(), UserActivity.class);
                    resultIntent.putExtra("chat_room_id", chatRoomId);
                    resultIntent.putExtra("userid",userID);
                    resultIntent.putExtra("name",uObj.getString("name"));
                    showNotificationMessage2(getApplicationContext(), title, user.getName() + " : " + message.getMessage(), message.getCreatedAt(), resultIntent);

                }
            } catch (JSONException e) {
                Log.e(TAG, "json parsing error: " + e.getMessage());
            }

        } else {
            // the push notification is silent, may be other operations needed
            // like inserting it in to SQLite
        }
    }

    /**
     * Showing notification with text only
     * */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     * */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }


    private void showNotificationMessage2(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils2 = new NotificationUtils2(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils2.showNotificationMessage2(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     * */
    private void showNotificationMessageWithBigImage2(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils2 = new NotificationUtils2(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils2.showNotificationMessage2(title, message, timeStamp, intent, imageUrl);
    }
}
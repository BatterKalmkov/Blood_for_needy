package com.example.bobby.blooddonation.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.bobby.blooddonation.model.User2;

public class MyPreferenceManager2 {

    private String TAG = MyPreferenceManager2.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref2;

    // Editor for Shared preferences
    SharedPreferences.Editor editor2;

    // Context
    Context _context2;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "gcm";

    // All Shared Preferences Keys
    private static final String KEY_USER_ID = "user_id2";
    private static final String KEY_USER_NAME = "user_name2";
    private static final String KEY_USER_EMAIL = "user_email2";
    private static final String KEY_NOTIFICATIONS = "notifications2";

    // Constructor
    public MyPreferenceManager2(Context context) {
        this._context2 = context;
        pref2 = _context2.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor2 = pref2.edit();
    }

    public void storeUser2(User2 user) {
        editor2.putString(KEY_USER_ID, user.getId());
        editor2.putString(KEY_USER_NAME, user.getName());
        editor2.putString(KEY_USER_EMAIL, user.getEmail());
        editor2.commit();

        Log.e(TAG, "User is stored in shared preferences. " + user.getName() + ", " + user.getEmail());
    }

    public User2 getUser2() {
        if (pref2.getString(KEY_USER_ID, null) != null) {
            String id, name, email;
            id = pref2.getString(KEY_USER_ID, null);
            name = pref2.getString(KEY_USER_NAME, null);
            email = pref2.getString(KEY_USER_EMAIL, null);

            User2 user2 = new User2(id, name, email);
            return user2;
        }
        return null;
    }

    public void addNotification2(String notification) {

        // get old notifications
        String oldNotifications2 = getNotifications2();

        if (oldNotifications2 != null) {
            oldNotifications2 += "|" + notification;
        } else {
            oldNotifications2 = notification;
        }

        editor2.putString(KEY_NOTIFICATIONS, oldNotifications2);
        editor2.commit();
    }

    public String getNotifications2() {
        return pref2.getString(KEY_NOTIFICATIONS, null);
    }

    public void clear2() {
        editor2.clear();
        editor2.commit();
    }

}
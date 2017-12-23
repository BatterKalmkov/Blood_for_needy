package com.example.bobby.blooddonation.app;
import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.bobby.blooddonation.helper.MyPreferenceManager;
import com.example.bobby.blooddonation.helper.MyPreferenceManager2;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class
            .getSimpleName();

    private RequestQueue mRequestQueue;

    private static MyApplication mInstance;

    private MyPreferenceManager pref;

    public static final String TAG2 = MyApplication.class
            .getSimpleName();

    private RequestQueue mRequestQueue2;

    private static MyApplication mInstance2;

    private MyPreferenceManager2 pref2;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mInstance2=this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public MyPreferenceManager getPrefManager() {
        if (pref == null) {
            pref = new MyPreferenceManager(this);
        }

        return pref;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void logout() {
        pref.clear();
       // Intent intent = new Intent(this, LoginActivity.class);
       // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
       // startActivity(intent);
    }


    public static synchronized MyApplication getInstance2() {
        return mInstance2;
    }

    public RequestQueue getRequestQueue2() {
        if (mRequestQueue2 == null) {
            mRequestQueue2 = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue2;
    }

    public MyPreferenceManager2 getPrefManager2() {
        if (pref2 == null) {
            pref2 = new MyPreferenceManager2(this);
        }

        return pref2;
    }

    public <T> void addToRequestQueue2(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue2().add(req);
    }

    public <T> void addToRequestQueue2(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue2().add(req);
    }

    public void cancelPendingRequests2(Object tag) {
        if (mRequestQueue2 != null) {
            mRequestQueue2.cancelAll(tag);
        }
    }

    public void logout2() {
        pref2.clear2();
        // Intent intent = new Intent(this, LoginActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // startActivity(intent);
    }

}
package com.example.bobby.blooddonation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageButton;

import com.example.bobby.blooddonation.utility.DatabaseHelper;
import com.example.bobby.blooddonation.utility.FbDatabaseHelper;
import com.example.bobby.blooddonation.utility.FragmentDrawer;
import com.example.bobby.blooddonation.utility.GlDatabaseHelper;
import com.example.bobby.blooddonation.utility.TwDatabaseHelper;
import com.example.bobby.blooddonation.utility.testimonial;
import com.example.bobby.blooddonation.utility.updateprofile;
import com.example.bobby.blooddonation.utility.whydonate;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{
ImageButton ib1,ib2,ib3,ib4,ib5,ib6,ib7,ib8;
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    private static final String TWITTER_KEY = "YOUR API KEY";
    private static final String TWITTER_SECRET = "YOUR API SECRET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.activity_main);

        ib1=(ImageButton)findViewById(R.id.imageButton2);
        ib2=(ImageButton)findViewById(R.id.imageButton3);
        ib3=(ImageButton)findViewById(R.id.imageButton4);
        ib4=(ImageButton)findViewById(R.id.imageButton5);
        ib5=(ImageButton)findViewById(R.id.imageButton6);
        ib6=(ImageButton)findViewById(R.id.imageButton7);
        ib7=(ImageButton)findViewById(R.id.imageButton8);
        ib8=(ImageButton)findViewById(R.id.imageButton9);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        displayView(0);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,search.class);
                startActivity(intent);
            }
        });

        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,needdonor.class);
                startActivity(intent);
            }
        });

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,timeline.class);
                startActivity(intent);
            }
        });

        ib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,chat.class);
                startActivity(intent);
            }
        });

        ib5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,event.class);
                startActivity(intent);
            }
        });

        ib6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,blog.class);
                startActivity(intent);
            }
        });

        ib7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,gethappiness.class);
                startActivity(intent);
            }
        });

        ib8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,referral.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 1:
                fragment = new updateprofile();
                break;
            case 2:
                fragment = new whydonate();
                break;
            case 3:
                fragment = new testimonial();
                break;
            case 4:

               DatabaseHelper dh=new DatabaseHelper(MainActivity.this);
               FbDatabaseHelper dh1=new FbDatabaseHelper(MainActivity.this);
               TwDatabaseHelper  dh2=new TwDatabaseHelper(MainActivity.this);
               GlDatabaseHelper dh3=new GlDatabaseHelper(MainActivity.this);

                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();

                if(accessToken != null) {
                    if(SplashScreen1.fblogout==true)
                    {
                        SplashScreen1.fblogout=false;
                        dh1.deletedata(1);
                        LoginManager.getInstance().logOut();
                    }
                }

                if (twitterSession != null)
                {
                    if(SplashScreen1.twlogout==true) {
                        SplashScreen1.twlogout=false;
                        dh2.deletedata(1);
                        CookieSyncManager.createInstance(MainActivity.this);
                        CookieManager cookieManager = CookieManager.getInstance();
                        cookieManager.removeSessionCookie();
                        Twitter.getSessionManager().clearActiveSession();
                        Twitter.logOut();
                    }
                }

                if(SplashScreen1.logout==true)
                {
                    SplashScreen1.logout=false;
                    dh.deletedata(1);
                }

                if(SplashScreen1.gllogout==true)
                {
                    SplashScreen1.gllogout=false;
                    dh3.deletedata(1);

                }
                Intent i=new Intent(MainActivity.this, Signin.class);
                startActivity(i);
                finish();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder b2=new AlertDialog.Builder(MainActivity.this)
                .setMessage("Do you really want to close this app?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog b3=b2.create();
        b3.setTitle("Exit");
        b3.show();
    }
}

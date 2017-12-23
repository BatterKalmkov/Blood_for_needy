package com.example.bobby.blooddonation;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bobby.blooddonation.utility.DatabaseHelper;
import com.example.bobby.blooddonation.utility.FbDatabaseHelper;
import com.example.bobby.blooddonation.utility.GlDatabaseHelper;
import com.example.bobby.blooddonation.utility.TwDatabaseHelper;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.google.android.gms.auth.api.Auth;


import io.fabric.sdk.android.Fabric;

public class Signin extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener{
    ImageView iv2;
    Button b1,b2,b3;
    EditText t1,t2;
    static String username,password;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    String fbemail="",fbid="",fbname="",fbpic="",fbgender="";

    private TwitterLoginButton twloginButton;
    private static final String TWITTER_KEY = "sWnwTsWpzxPSW3VSFZJYrRsFZ";
    private static final String TWITTER_SECRET = "4nZDNamT98rhR5YEo544YLP9ExULxxBteFDxkGi3weVrazByFy";
    String twpic="",twname="",twemail=" ";
    long twid;

    private SignInButton signInButton;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 100;
    String googlepic="",googlename="",googleid="",googleemail="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        setContentView(R.layout.signin);

        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.login_button);

        twloginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(this);

        iv2=(ImageView)findViewById(R.id.imageView2);
        iv2.setImageResource(R.drawable.signin);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        b3=(Button)findViewById(R.id.button4);
        t1=(EditText) findViewById(R.id.editText);
        t2=(EditText) findViewById(R.id.editText2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                username=t1.getText().toString();
                password=t2.getText().toString();

                if(t1.getText().toString().isEmpty() || t2.getText().toString().isEmpty())
                {
                    Toast.makeText(Signin.this, "Please fill all the details.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    new JSONP().execute();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signin.this,Signup.class);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signin.this,Forgetpassword.class);
                startActivity(intent);
            }
        });
    }

   public class  JSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl ="http://sixthsenseit.com/blood/signin.php";

        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("username") + "=" + URLEncoder.encode(username) + "&" + URLEncoder.encode("password") + "=" + URLEncoder.encode(password);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder  stringBuilder=new StringBuilder();
                while((jsonstring= bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(jsonstring+"\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                data=stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.contains("logged"))
            {
                if(SplashScreen1.logout==false)
                {
                    SplashScreen1.logout=true;
                    DatabaseHelper dh = new DatabaseHelper(Signin.this);
                    boolean b = dh.deletedata(1);
                    boolean b1 = dh.insertdata(username, password,SplashScreen1.logout.toString());
                    Intent bj=new Intent(Signin.this,MainActivity.class);
                    bj.putExtra("username",username);
                    startActivity(bj);
                    finish();
                }
            }
            else
            {
                Toast.makeText(Signin.this,s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        twloginButton.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                        {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {
                                if (response.getError() != null) {

                                }
                                else
                                {
                                    fbemail = me.optString("email");
                                    fbid = me.optString("id");
                                    fbname = me.optString("name");
                                    fbpic="https://graph.facebook.com/"+fbid +"/picture?type=normal";
                                    fbgender = me.optString("gender");

                                    if(SplashScreen1.fblogout==false) {
                                        SplashScreen1.fblogout=true;
                                        FbDatabaseHelper dh1 = new FbDatabaseHelper(Signin.this);
                                        boolean b = dh1.deletedata(1);
                                        boolean b1 = dh1.insertdata(SplashScreen1.fblogout.toString(),fbid,fbname,fbpic,fbemail,fbgender);
                                    }
                                    new fbJSONP().execute();
                                }
                            }
                        }).executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(Signin.this, "Login attempt failed", Toast.LENGTH_SHORT).show();
            }

        });

        twloginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;

                 twname=session.getUserName();
                 twid=session.getUserId();

                Twitter.getApiClient(session).getAccountService()
                        .verifyCredentials(true, false, new Callback<User>() {

                            @Override
                            public void failure(TwitterException e) {
                                Toast.makeText(Signin.this, "Unable to retrieve user data", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void success(Result<User> userResult) {
                                User user = userResult.data;
                                twpic = user.profileImageUrl;
                                //twemail=user.email;

                                if(SplashScreen1.twlogout==false) {
                                    SplashScreen1.twlogout=true;
                                    TwDatabaseHelper dh2 = new TwDatabaseHelper(Signin.this);
                                    boolean b = dh2.deletedata(1);
                                    boolean b1 = dh2.insertdata(SplashScreen1.twlogout.toString(), String.valueOf(twid),twname,twpic);
                                }
                                new twJSONP().execute();
                            }
                        });
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            signIn();
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess())
        {
            GoogleSignInAccount acct = result.getSignInAccount();
            googlename= acct.getDisplayName();
            googleemail= acct.getEmail();
            if(acct.getPhotoUrl()!=null)
            {
                googlepic=acct.getPhotoUrl().toString();
            }
            googleid= acct.getId();

            if(SplashScreen1.gllogout==false) {
                SplashScreen1.gllogout=true;
                GlDatabaseHelper dh3 = new GlDatabaseHelper(Signin.this);
                boolean b = dh3.deletedata(1);
                boolean b1 = dh3.insertdata(SplashScreen1.gllogout.toString(),googleid,googlename,googlepic,googleemail);
            }
            new glJSONP().execute();
        }
        else
        {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    class fbJSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://sixthsenseit.com/blood/fbsignin.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("fbname") + "=" + URLEncoder.encode(fbname) + "&" + URLEncoder.encode("fbid") + "=" + URLEncoder.encode(fbid)
                        + "&" + URLEncoder.encode("fbpic") + "=" + URLEncoder.encode(fbpic) + "&" + URLEncoder.encode("fbgender") + "=" + URLEncoder.encode(fbgender)
                        + "&" + URLEncoder.encode("fbemail") + "=" + URLEncoder.encode(fbemail);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                data = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Intent bj = new Intent(Signin.this, MainActivity.class);
            bj.putExtra("fbname", fbname);
            startActivity(bj);
            finish();
        }
    }


    class twJSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://sixthsenseit.com/blood/twsignin.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("twname") + "=" + URLEncoder.encode(twname) + "&" + URLEncoder.encode("twid") + "=" + URLEncoder.encode(String.valueOf(twid))
                        + "&" + URLEncoder.encode("twpic") + "=" + URLEncoder.encode(twpic)+ "&" + URLEncoder.encode("twemail") + "=" + URLEncoder.encode(twemail);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                data = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Intent bj = new Intent(Signin.this, MainActivity.class);
            bj.putExtra("twname",twname);
            startActivity(bj);
            finish();
        }
    }

    class glJSONP extends AsyncTask<Void,Void,String> {
        String jsonurl, jsonstring;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonurl = "http://sixthsenseit.com/blood/glsignin.php";
        }

        @Override
        protected String doInBackground(Void... params) {

            String data = "";
            try {
                URL url = new URL(jsonurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                String postdata = URLEncoder.encode("glname") + "=" + URLEncoder.encode(googlename) + "&" + URLEncoder.encode("glid") + "=" + URLEncoder.encode(String.valueOf(googleid))
                        + "&" + URLEncoder.encode("glpic") + "=" + URLEncoder.encode(googlepic)+ "&" + URLEncoder.encode("glemail") + "=" + URLEncoder.encode(googleemail);

                bufferedWriter.write(postdata);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((jsonstring = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonstring + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                data = stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient);
            Auth.GoogleSignInApi.signOut(mGoogleApiClient);

            Intent bj = new Intent(Signin.this, MainActivity.class);
            bj.putExtra("glname",googlename);
            startActivity(bj);
            finish();
        }
    }
}

package com.shrenik.android.app.wamp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private Button mWatchButton;
    private Button mStopButton;
    private Button mResetButton;
    private WebView mWebView;
    private ProgressBar mProgressBar;

    private Button mStartStreamButton;
    private Button mStopStreamButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("  "+"Wamp");



        mProgressBar = (ProgressBar)findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);

        mWatchButton = (Button)findViewById(R.id.watch_button);

        mWatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);

            }
        });

        mStopButton = (Button)findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Display yes or no dialog--------------------------------------------------------------------------------

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Please Confirm");
                builder.setMessage("Are you sure you want to cancel the print?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // send http request. Send the string "off"
                        // Display the progress bar
                        mProgressBar.setVisibility(View.VISIBLE);
                        // We add the printmew4tch string to prevent external stop commands and give more security
                        new StateAsyncTask().execute("off_printmew4tch");

                        // close the dialog
                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing and close the dialog
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                //dialog ends ----------------------------------------------------------------------------------------------
            }
        });

        mResetButton = (Button)findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Display yes or no dialog--------------------------------------------------------------------------------

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Please Confirm");
                builder.setMessage("Are you sure you want to reset the print?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // send http request. Send the string "on"
                        // Display the progress bar
                        mProgressBar.setVisibility(View.VISIBLE);
                        // We add the printmew4tch string to prevent external stop commands and give more security
                        new StateAsyncTask().execute("on_printmew4tch");

                        // close the dialog
                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing and close
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                //dialog ends ----------------------------------------------------------------------------------------------
            }
        });

        mStartStreamButton = (Button)findViewById(R.id.start_stream_button);
        mStartStreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // Display yes or no dialog--------------------------------------------------------------------------------

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Please Confirm");
                builder.setMessage("Are you sure you want to start the stream?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // send http request. Send the string "off"
                        // Display the progress bar
                        mProgressBar.setVisibility(View.VISIBLE);
                        // We add the printmew4tch string to prevent external stop commands and give more security
                        new StateAsyncTask().execute("on_stream_printmew4tch");

                        // close the dialog
                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing and close the dialog
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                //dialog ends ----------------------------------------------------------------------------------------------




            }
        });

        mStopStreamButton = (Button)findViewById(R.id.stop_stream_button);
        mStopStreamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // Display yes or no dialog--------------------------------------------------------------------------------

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setTitle("Please Confirm");
                builder.setMessage("Are you sure you want to stop the stream?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // send http request. Send the string "off"
                        // Display the progress bar
                        mProgressBar.setVisibility(View.VISIBLE);
                        // We add the printmew4tch string to prevent external stop commands and give more security
                        new StateAsyncTask().execute("off_stream_printmew4tch");

                        // close the dialog
                        dialog.dismiss();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing and close the dialog
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

                //dialog ends ----------------------------------------------------------------------------------------------



            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_credentials)
        {
            Intent i = new Intent(getApplicationContext(),ChangeCredentialsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    // Async task
    private class StateAsyncTask extends AsyncTask<String, Integer, Double>
    {
        @Override
        protected Double doInBackground(String... params)
        {
            postData(params[0]);
            return null;
        }

        protected void onPostExecute(Double result)
        {
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Command Sent", Toast.LENGTH_SHORT).show();
        }

        protected void onProgressUpdate(Integer... progress)
        {
            mProgressBar.setProgress(progress[0]);
        }

        public void postData(String state_string)
        {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.watchmeprintagain.webege.com/php_files/button_send.php");

            try
            {
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
                nameValuePair.add(new BasicNameValuePair("state",state_string));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                HttpResponse response = httpclient.execute(httppost);
            }
            catch(ClientProtocolException e)
            {

            }
            catch(IOException e)
            {

            }
        }
    }
}

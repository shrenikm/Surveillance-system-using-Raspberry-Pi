package com.shrenik.android.app.wamp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ChangeCredentialsActivity extends ActionBarActivity {

    EditText mChange_username;
    EditText mChange_password;
    Button mChangeButton;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_change);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("  "+"Change Credentials");

        mChange_username = (EditText)findViewById(R.id.text_username_change);
        mChange_password = (EditText)findViewById(R.id.text_password_change);
        mChangeButton = (Button)findViewById(R.id.change_button);



        mChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Get the username and password entered
                final String user_file = "username_file.txt";
                final String user_username = mChange_username.getText().toString().trim();
                final String pwd_file = "password_file.txt";
                final String user_password = mChange_password.getText().toString().trim();

                // Display the dialog only if the user has entered username and password

                if ((!user_username.matches("")) && (!user_password.matches(""))) {


                    // Display Yes or No window-----------------------------------------------------------------


                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                    builder.setTitle("Please Confirm");
                    builder.setMessage("Are you sure you want to change the username and password?");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            // Store the new username and password in the files


                            FileOutputStream outputStream;


                            try {

                                outputStream = openFileOutput(user_file, Context.MODE_PRIVATE);
                                outputStream.write(user_username.getBytes());

                                outputStream = openFileOutput(pwd_file, Context.MODE_PRIVATE);
                                outputStream.write(user_password.getBytes());

                                outputStream.close();

                                // Respond to the update
                                Toast.makeText(getApplicationContext(), "Username and Password has been updated", Toast.LENGTH_LONG).show();

                                // We finish this activity and return the user to the previous main activity
                                finish();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


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


                } else {
                    Toast.makeText(getApplicationContext(), "Please enter the new username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

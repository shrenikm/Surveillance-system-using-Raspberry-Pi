package com.shrenik.android.app.wamp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;

public class LoginActivity extends ActionBarActivity{

    private Button mLoginButton;
    private EditText mUsername;
    private EditText mPassword;

    private String username;
    private String password;

//    LoginCredentials mLoginCredentials = new LoginCredentials();
//
//    public String correct_username = mLoginCredentials.getCorrect_username();
//    public String correct_password = mLoginCredentials.getCorrect_password();

    public String correct_username;
    public String correct_password;



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle("  "+"Login");


        mUsername = (EditText)findViewById(R.id.username);
        mPassword = (EditText)findViewById(R.id.password);


        // Read the username from the file

        try {

            FileInputStream inputStream = openFileInput("username_file.txt");

            int pos;
            correct_username="";
            while( (pos = inputStream.read()) != -1){
                correct_username = correct_username + Character.toString((char)pos);
            }
            inputStream.close();

        }
        catch(Exception e)
        {
            // If no file exists, the user has not update his username. This throws an error and
            // the default username of "watchmeprint" is sent to it.
            correct_username="watchmeprint";
        }




        // read the password from the file

        try {

            FileInputStream inputStream = openFileInput("password_file.txt");

            int pos;
            correct_password="";
            while( (pos = inputStream.read()) != -1){
                correct_password = correct_password + Character.toString((char)pos);
            }
            inputStream.close();

        }
        catch(Exception e)
        {
            // If no file exists, it throws an error. Thus the default password of "print" is sent
            correct_password="print";
        }



        mLoginButton = (Button)findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = mUsername.getText().toString().trim();
                password = mPassword.getText().toString().trim();

                if(username.equals(correct_username)&&password.equals(correct_password))
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(username.equals(correct_username)&&(!password.equals(correct_password)))
                {
                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                }

                if((!username.equals(correct_username))&&password.equals(correct_password))
                {
                    Toast.makeText(getApplicationContext(), "Wrong Username", Toast.LENGTH_SHORT).show();
                }

                if((!username.equals(correct_username))&&(!password.equals(correct_password)))
                {
                    Toast.makeText(getApplicationContext(), "Wrong Username and Password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

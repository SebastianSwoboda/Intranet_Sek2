package com.kushberg.myapplication;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public class LoginActivity extends AppCompatActivity {

    String password;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = new Intent(this, MainActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button)findViewById(R.id.email_sign_in_button);
        EditText emailField  = (EditText)findViewById(R.id.email);
        EditText passwordField = (EditText)findViewById(R.id.password);


        loginButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                          username = emailField.getText().toString();

                         password = passwordField.getText().toString() ;


                        startActivity(intent);
                    }
                });




    }

    /*

    public void setLogin(){
        try {
            Connection.Response res = Jsoup.connect("https://intranet.tam.ch/bmz")
                .data("loginuser", username, "loginpassword", password)
                .method(Connection.Method.POST)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Map<String, String> loginCookies = res.cookies();


    Document doc;

    {
        try {
            doc = Jsoup.connect("urlYouNeedToBeLoggedInToAccess")
                        .cookies(loginCookies)
                        .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */
}



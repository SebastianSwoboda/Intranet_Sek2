package com.kushberg.myapplication;


import android.content.Intent;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.nodes.Document;

public class LoginActivity extends AppCompatActivity {

    String finalUrl;

    com.kushberg.myapplication.Connection connection = new com.kushberg.myapplication.Connection();

    /*
    String password;
    String username;
    */


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
                        /*
                        username = emailField.getText().toString();

                        password = passwordField.getText().toString() ;
                        */

                            connection.doInBackground();



                         if (connection.finalUrl.equals("https://intranet.tam.ch/tbz/calendar?menuitem=Stundenplan")){
                             startActivity(intent);

                         }else{
                            System.out.println("You fucked up");

                         }



                    }
                });








    }





/*
    public void test(){

        final String USER_AGENT = "Mozilla";

        String loginFormUrl = "https://intranet.tam.ch/";
        String loginActionUrl = "https://intranet.tam.ch/";
        String timeTableUrl = "https://intranet.tam.ch/tbz/calendar";
        String loginuser = "sebastian.swoboda@edu.tbz.ch";
        String loginpassword = "Sebolanawurscht9";
        String loginschool = "tbz";

        Map<String, String> cookies = new HashMap<>();
        Map<String, String> formData = new HashMap<>();
        Map<String, String> timeTableAccess = new HashMap<>();




        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    org.jsoup.Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(org.jsoup.Connection.Method.GET).userAgent(USER_AGENT).execute();
                    Document loginDoc = loginForm.parse();

                    cookies.putAll(loginForm.cookies());






                    formData.put("Button","Anmelden");
                    formData.put("utf8","e2 9c 93");
                    formData.put("loginuser",loginuser);
                    formData.put("loginpassword",loginpassword);
                    formData.put("loginschool",loginschool);

                    org.jsoup.Connection.Response homePage = null;

                    homePage = Jsoup.connect(loginActionUrl)
                            .cookies(cookies)
                            .data(formData)
                            .method(org.jsoup.Connection.Method.POST)
                            .userAgent(USER_AGENT)

                            .execute();


                    cookies.putAll(homePage.cookies());

                    timeTableAccess.put("menuitem","Stundenplan");

                    org.jsoup.Connection.Response timetablePage = Jsoup.connect(timeTableUrl)
                            .cookies(cookies)
                            .data(timeTableAccess)
                            .method(org.jsoup.Connection.Method.GET)
                            .userAgent(USER_AGENT)
                            .execute();

                    finalUrl = timetablePage.url().toString();



                    System.out.println(timetablePage.parse().html());
                    Document timeTableDocument = timetablePage.parse();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }*/





}



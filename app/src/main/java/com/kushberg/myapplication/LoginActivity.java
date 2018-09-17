package com.kushberg.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
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

public class LoginActivity extends AppCompatActivity  {

    String finalUrl;

    String password;
    String username;

    EditText emailField;
    EditText passwordField;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        intent = new Intent(this, MainActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        Button loginButton = (Button)findViewById(R.id.email_sign_in_button);
        emailField  = (EditText)findViewById(R.id.email);
        passwordField = (EditText)findViewById(R.id.password);

        loginButton.setOnClickListener(listener);

        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

        dlgAlert.setMessage("wrong password or username");
        dlgAlert.setTitle("Error Message...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);

        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }});
    }

    public class Connection2 extends AsyncTask<Void, Void, Void> {

        final String USER_AGENT = "Mozilla";

        String loginFormUrl = "https://intranet.tam.ch/";
        String loginActionUrl = "https://intranet.tam.ch/";
        String timeTableUrl = "https://intranet.tam.ch/tbz/calendar";
        String loginschool = "tbz";

        Map<String, String> cookies = new HashMap<>();
        Map<String, String> formData = new HashMap<>();
        Map<String, String> timeTableAccess = new HashMap<>();

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                org.jsoup.Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(org.jsoup.Connection.Method.GET).userAgent(USER_AGENT).execute();
                Document loginDoc = loginForm.parse();

                cookies.putAll(loginForm.cookies());

                formData.put("Button", "Anmelden");
                formData.put("utf8", "e2 9c 93");
                formData.put("loginuser", username);
                formData.put("loginpassword", password);
                formData.put("loginschool", loginschool);

                org.jsoup.Connection.Response homePage = Jsoup.connect(loginActionUrl)
                        .cookies(cookies)
                        .data(formData)
                        .method(org.jsoup.Connection.Method.POST)
                        .userAgent(USER_AGENT)

                        .execute();

                cookies.putAll(homePage.cookies());

                timeTableAccess.put("menuitem", "Stundenplan");

                org.jsoup.Connection.Response timetablePage = Jsoup.connect(timeTableUrl)
                        .cookies(cookies)
                        .data(timeTableAccess)
                        .method(org.jsoup.Connection.Method.GET)
                        .userAgent(USER_AGENT)
                        .execute();

                finalUrl = timetablePage.url().toString();
/*
                System.out.println(timetablePage.parse().html());
                Document timeTableDocument = timetablePage.parse();
                */
                return null;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if(finalUrl.equals("https://intranet.tam.ch/tbz/calendar?menuitem=Stundenplan")){
                startActivity(intent);
            }else{


        }
    }
}
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            username = emailField.getText().toString();

            password = passwordField.getText().toString() ;
            new Connection2().execute();
            /*

            if (finalUrl.equals("https://intranet.tam.ch/tbz/calendar?menuitem=Stundenplan")){
                startActivity(intent);

            }else{
                System.out.println("error");}*/

        }
    };
}



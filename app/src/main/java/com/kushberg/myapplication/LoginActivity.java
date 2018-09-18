package com.kushberg.myapplication;


import android.content.Intent;
import android.os.AsyncTask;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    String finalUrl;

    String password;
    String username;

    EditText emailField;
    EditText passwordField;

    Intent intent;

    AlertDialog.Builder dlgAlert;

    Elements loginElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        intent = new Intent(this, MainActivity.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        Button loginButton = findViewById(R.id.email_sign_in_button);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);

        loginButton.setOnClickListener(listener);

        dlgAlert = new AlertDialog.Builder(this);

        dlgAlert.setMessage("wrong password or username");
        dlgAlert.setTitle("Error Message...");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);

        dlgAlert.setPositiveButton("Ok",
                (dialog, which) -> {

                });
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
                Document homePageDocument = homePage.parse();

                loginElements = homePageDocument.select("a[onclick][href]");

                cookies.putAll(homePage.cookies());

                timeTableAccess.put("menuitem", "Stundenplan");

                org.jsoup.Connection.Response timetablePage = Jsoup.connect(timeTableUrl)
                        .cookies(cookies)
                        .data(timeTableAccess)
                        .method(org.jsoup.Connection.Method.GET)
                        .userAgent(USER_AGENT)
                        .execute();

                finalUrl = timetablePage.url().toString();

                return null;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (loginElements.toString().equals("")) {
                dlgAlert.show();
            } else {
                startActivity(intent);
            }
        }
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            username = emailField.getText().toString();

            password = passwordField.getText().toString();
            new Connection2().execute();
        }
    };
}



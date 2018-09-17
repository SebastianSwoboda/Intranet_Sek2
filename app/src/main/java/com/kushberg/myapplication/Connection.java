package com.kushberg.myapplication;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Connection extends AsyncTask<Void, Void, Void> {

    LoginActivity loginActivity;

    final String USER_AGENT = "Mozilla";

    String loginFormUrl = "https://intranet.tam.ch/";
    String loginActionUrl = "https://intranet.tam.ch/";
    String timeTableUrl = "https://intranet.tam.ch/tbz/calendar";
    String loginschool = "tbz";

    Map<String, String> cookies = new HashMap<>();
    Map<String, String> formData = new HashMap<>();
    Map<String, String> timeTableAccess = new HashMap<>();

    String finalUrl;

    @Override
    protected Void doInBackground(Void... voids) {

        try {

            org.jsoup.Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(org.jsoup.Connection.Method.GET).userAgent(USER_AGENT).execute();
            Document loginDoc = loginForm.parse();

            cookies.putAll(loginForm.cookies());

            formData.put("Button", "Anmelden");
            formData.put("utf8", "e2 9c 93");
            formData.put("loginuser", loginActivity.username);
            formData.put("loginpassword", loginActivity.password);
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

            System.out.println(timetablePage.parse().html());
            Document timeTableDocument = timetablePage.parse();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        loginActivity.finalUrl = finalUrl;




    }
}

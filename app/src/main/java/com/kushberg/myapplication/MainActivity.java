package com.kushberg.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView timetableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timetableList = (ListView)findViewById(R.id.timetableList);

        List<String> subjects = new ArrayList<String>();
        subjects.add("Test");
        subjects.add("Test");
        subjects.add("Test");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                subjects );

        timetableList.setAdapter(adapter);
    }

    public void webpageConnection() throws IOException {
        final String USER_AGENT = "\"Mozilla/5.0 (Windows NT\" +\n" +
                "          \" 6.1; WOW64) AppleWebKit/535.2 (KHTML, like Gecko) Chrome/15.0.874.120 Safari/535.2\"";

        String loginFormUrl = "https://intranet.tam.ch/";
        String loginActionUrl = "https://intranet.tam.ch/";
        String timeTableUrl = "https://intranet.tam.ch/tbz/calendar";
        String loginuser = "sebastian.swoboda@edu.tbz.ch";
        String loginpassword = "Sebolanawurscht9";
        String loginschool = "tbz";

        Map<String, String> cookies = new HashMap<>();
        Map<String, String> formData = new HashMap<>();
        Map<String, String> timeTableAccess = new HashMap<>();

        Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET).userAgent(USER_AGENT).execute();
        Document loginDoc = loginForm.parse();

        cookies.putAll(loginForm.cookies());



        formData.put("Button", "Anmelden");
        formData.put("utf8", "e2 9c 93");
        formData.put("loginuser", loginuser);
        formData.put("loginpassword", loginpassword);
        formData.put("loginschool", loginschool);

        Connection.Response homePage = Jsoup.connect(loginActionUrl)
                .cookies(cookies)
                .data(formData)
                .method(Connection.Method.POST)
                .userAgent(USER_AGENT)

                .execute();

        cookies.putAll(homePage.cookies());

        timeTableAccess.put("menuitem", "Stundenplan");

        Connection.Response timetablePage = Jsoup.connect(timeTableUrl)
                .cookies(cookies)
                .data(timeTableAccess)
                .method(Connection.Method.GET)
                .userAgent(USER_AGENT)
                .execute();

        System.out.println(timetablePage.parse().html());
        Document timeTableDocument = timetablePage.parse();
        Boolean lol = false;

        lol = timeTableDocument.hasClass("k-scheduler-table");
        System.out.println(lol);





    }









}


package com.kushberg.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.IOException;

public class Test extends AppCompatActivity {
    String title;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        connection();
        textView = findViewById(R.id.sdweedew);
        textView.setText(title);
    }

    public void connection() {
        Thread downloadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                org.jsoup.nodes.Document doc;
                try {
                    doc = Jsoup.connect("https://github.com/").get();
                    String title = doc.title();
                    System.out.print(title);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            });downloadThread.start();
        }



    /*
        try{
        String url = "https://github.com/";

        org.jsoup.Connection.Response homePage = Jsoup.connect(url).execute();
        URL = homePage.url().toString();}
        catch (IOException e){

        }*/







    }


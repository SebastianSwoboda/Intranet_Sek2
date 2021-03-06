package com.kushberg.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.io.IOException;

public class Test extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        button = findViewById(R.id.test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectionTest().execute();
               


            }
        });
    }

    public class ConnectionTest extends AsyncTask<Void, Void, Void> {

/*
    @Override
    protected Void doInBackground(Void... voids) {
        try{
            Connection.Response loginForm = Jsoup.connect(loginFormUrl).method(Connection.Method.GET)
                    .execute();
            title = loginForm.toString();
            System.out.print(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/


        String words, words2;

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                org.jsoup.nodes.Document doc = Jsoup.connect("https://www.stadt-zuerich.ch/ssd/de/index/sport/schwimmen/sommerbaeder/flussbad_au_hoengg.html").get();

                words = doc.select("#baederinfos_temperature_value").text();
                words2 =doc.select("#baederinfos_status_updated").text();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            button.setText(words);



        }
    }




}


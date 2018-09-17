package com.kushberg.myapplication;

import android.os.AsyncTask;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ConnectionTest extends AsyncTask<Void, Void, Void> {
LoginActivity loginActivity;
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

                Document doc = Jsoup.connect("https://www.stadt-zuerich.ch/ssd/de/index/sport/schwimmen/sommerbaeder/flussbad_au_hoengg.html").get();

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




        }
    }











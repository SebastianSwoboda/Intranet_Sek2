package com.kushberg.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

import android.provider.DocumentsContract;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);




    }

    public void setLogin(){
        Connection.Response res = Jsoup.connect("https://intranet.tam.ch/bmz")
            .data("loginuser", "myUsername", "loginpassword", "myPassword")
            .method(Connection.Method.POST)
            .execute();}

    Map<String, String> loginCookies = res.cookies();


    Document doc = Jsoup.connect("urlYouNeedToBeLoggedInToAccess")
            .cookies(loginCookies)
            .get();


}



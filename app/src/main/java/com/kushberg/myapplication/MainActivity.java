package com.kushberg.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

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


}


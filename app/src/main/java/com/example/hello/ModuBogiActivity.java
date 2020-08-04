package com.example.hello;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ModuBogiActivity extends AppCompatActivity {
    MainActivity.CommentAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modu_bogi);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

}

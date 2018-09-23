package com.example.android.drum_1.Activity;

import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.drum_1.Adapter;
import com.example.android.drum_1.NetworkTask;
import com.example.android.drum_1.R;

public class MenuActivity extends AppCompatActivity {

    Adapter adapter;
    ViewPager viewPager;
    public static NetworkTask socketIns;
    private int port = 9999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //imageButton = (ImageButton)findViewById(R.id.imageButton);
        viewPager = (ViewPager)findViewById(R.id.view);
        adapter = new Adapter(this);
        viewPager.setAdapter(adapter);

        Intent intent = getIntent();
        String ip = intent.getStringExtra("ipAddress");
        socketIns = new NetworkTask(ip,port);
        socketIns.execute();
    }
}

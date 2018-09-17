package com.example.android.drum_1;

import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends AppCompatActivity {

    Adapter adapter;
    ViewPager viewPager;
    public static NetworkTask socketIns;
    private String ip = "172.20.10.6";
    private int port = 9999;
    public static int currentPos;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //imageButton = (ImageButton)findViewById(R.id.imageButton);
        viewPager = (ViewPager)findViewById(R.id.view);
        adapter = new Adapter(this);
        viewPager.setAdapter(adapter);



        socketIns = new NetworkTask(ip,port);
        socketIns.execute();
    }





}

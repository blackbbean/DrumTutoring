package com.example.android.drum_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BeforeActivity extends AppCompatActivity {

    private Button select1Btn;
    private Button select2Btn;
    private Button select3Btn;
    private Button select4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);
        select1Btn = (Button)findViewById(R.id.select1);
        select2Btn = (Button)findViewById(R.id.select2);
        select3Btn = (Button)findViewById(R.id.select3);
        select4Btn = (Button)findViewById(R.id.select4);


        select1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rudi1 = new Intent(BeforeActivity.this,selectActivity.class);
                startActivity(rudi1);
            }
        });

        select2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rudi2 = new Intent(BeforeActivity.this,select2Activity.class);
                startActivity(rudi2);
            }
        });

        select3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rudi3 = new Intent(BeforeActivity.this, select3Activity.class);
                startActivity(rudi3);
            }
        });

        select4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rudi4 = new Intent(BeforeActivity.this, select4Activity.class);
                startActivity(rudi4);
            }
        });



    }
}

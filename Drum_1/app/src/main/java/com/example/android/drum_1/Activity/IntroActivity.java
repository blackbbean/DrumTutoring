package com.example.android.drum_1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.drum_1.R;


public class IntroActivity extends AppCompatActivity {

    private EditText inputIP;
    private Button joinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        inputIP = (EditText)findViewById(R.id.ipText);
        joinBtn = (Button)findViewById(R.id.joinBtn);




        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                final String ip = inputIP.getText().toString();
                intent.putExtra("ipAddress",ip);
                startActivity(intent);
            }
        });
    }
}

package com.example.android.drum_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectActivity extends AppCompatActivity {

    Button[] rudiSelect = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        rudiSelect[0] = findViewById(R.id.rudi1Btn);
        rudiSelect[1] = findViewById(R.id.rudi2Btn);
        rudiSelect[2] = findViewById(R.id.rudi3Btn);
        rudiSelect[3] = findViewById(R.id.rudi4Btn);
        rudiSelect[4] = findViewById(R.id.rudi5Btn);
        rudiSelect[5] = findViewById(R.id.rudi6Btn);
        rudiSelect[6] = findViewById(R.id.rudi7Btn);
        rudiSelect[7] = findViewById(R.id.rudi8Btn);
        rudiSelect[8] = findViewById(R.id.rudi9Btn);
        rudiSelect[9] = findViewById(R.id.rudi10Btn);


        for(int i=0;i<10;i++) {

            rudiSelect[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickMethod(view);
                }
            });

        }
    }

    public void onClickMethod(View view){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        if(view == rudiSelect[0]){
            intent.putExtra("player","01");
        }
        else if(view == rudiSelect[1]){
            intent.putExtra("player","02");
        }
        else if(view == rudiSelect[2]){
            intent.putExtra("player","03");
        }
        else if(view == rudiSelect[3]){
            intent.putExtra("player","04");
        }
        else if(view == rudiSelect[4]){
            intent.putExtra("player","05");
        }
        else if(view == rudiSelect[5]){
            intent.putExtra("player","06");
        }
        else if(view == rudiSelect[6]){
            intent.putExtra("player","07");
        }
        else if(view == rudiSelect[7]){
            intent.putExtra("player","08");
        }
        else if(view == rudiSelect[8]){
            intent.putExtra("player","09");
        }
        else if(view == rudiSelect[9]){
            intent.putExtra("player","10");
        }
        startActivity(intent);
    }


}

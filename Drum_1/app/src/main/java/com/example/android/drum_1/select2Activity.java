package com.example.android.drum_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select2Activity extends AppCompatActivity {

    Button[] rudiSelect = new Button[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select2);

        rudiSelect[0] = findViewById(R.id.rudi11Btn);
        rudiSelect[1] = findViewById(R.id.rudi12Btn);
        rudiSelect[2] = findViewById(R.id.rudi13Btn);
        rudiSelect[3] = findViewById(R.id.rudi14Btn);
        rudiSelect[4] = findViewById(R.id.rudi15Btn);
        rudiSelect[5] = findViewById(R.id.rudi16Btn);
        rudiSelect[6] = findViewById(R.id.rudi17Btn);
        rudiSelect[7] = findViewById(R.id.rudi18Btn);
        rudiSelect[8] = findViewById(R.id.rudi19Btn);
        rudiSelect[9] = findViewById(R.id.rudi20Btn);

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
            intent.putExtra("player","11");
        }
        else if(view == rudiSelect[1]){
            intent.putExtra("player","12");
        }
        else if(view == rudiSelect[2]){
            intent.putExtra("player","13");
        }
        else if(view == rudiSelect[3]){
            intent.putExtra("player","14");
        }
        else if(view == rudiSelect[4]){
            intent.putExtra("player","15");
        }
        else if(view == rudiSelect[5]){
            intent.putExtra("player","16");
        }
        else if(view == rudiSelect[6]){
            intent.putExtra("player","17");
        }
        else if(view == rudiSelect[7]){
            intent.putExtra("player","18");
        }
        else if(view == rudiSelect[8]){
            intent.putExtra("player","19");
        }
        else if(view == rudiSelect[9]){
            intent.putExtra("player","20");
        }
        startActivity(intent);
    }



}

package com.example.android.drum_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select3Activity extends AppCompatActivity {

    Button[] rudiSelect = new Button[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select3);

        rudiSelect[0] = findViewById(R.id.rudi21Btn);
        rudiSelect[1] = findViewById(R.id.rudi22Btn);
        rudiSelect[2] = findViewById(R.id.rudi23Btn);
        rudiSelect[3] = findViewById(R.id.rudi24Btn);
        rudiSelect[4] = findViewById(R.id.rudi25Btn);
        rudiSelect[5] = findViewById(R.id.rudi26Btn);
        rudiSelect[6] = findViewById(R.id.rudi27Btn);
        rudiSelect[7] = findViewById(R.id.rudi28Btn);
        rudiSelect[8] = findViewById(R.id.rudi29Btn);
        rudiSelect[9] = findViewById(R.id.rudi30Btn);

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
            intent.putExtra("player","21");
        }
        else if(view == rudiSelect[1]){
            intent.putExtra("player","22");
        }
        else if(view == rudiSelect[2]){
            intent.putExtra("player","23");
        }
        else if(view == rudiSelect[3]){
            intent.putExtra("player","24");
        }
        else if(view == rudiSelect[4]){
            intent.putExtra("player","25");
        }
        else if(view == rudiSelect[5]){
            intent.putExtra("player","26");
        }
        else if(view == rudiSelect[6]){
            intent.putExtra("player","27");
        }
        else if(view == rudiSelect[7]){
            intent.putExtra("player","28");
        }
        else if(view == rudiSelect[8]){
            intent.putExtra("player","29");
        }
        else if(view == rudiSelect[9]){
            intent.putExtra("player","30");
        }
        startActivity(intent);
    }
}

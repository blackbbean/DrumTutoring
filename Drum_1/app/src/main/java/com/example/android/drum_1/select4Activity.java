package com.example.android.drum_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class select4Activity extends AppCompatActivity {

    Button[] rudiSelect = new Button[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select4);

        rudiSelect[0] = findViewById(R.id.rudi31Btn);
        rudiSelect[1] = findViewById(R.id.rudi32Btn);
        rudiSelect[2] = findViewById(R.id.rudi33Btn);
        rudiSelect[3] = findViewById(R.id.rudi34Btn);
        rudiSelect[4] = findViewById(R.id.rudi35Btn);
        rudiSelect[5] = findViewById(R.id.rudi36Btn);
        rudiSelect[6] = findViewById(R.id.rudi37Btn);
        rudiSelect[7] = findViewById(R.id.rudi38Btn);
        rudiSelect[8] = findViewById(R.id.rudi39Btn);
        rudiSelect[9] = findViewById(R.id.rudi40Btn);

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
            intent.putExtra("player","31");
        }
        else if(view == rudiSelect[1]){
            intent.putExtra("player","32");
        }
        else if(view == rudiSelect[2]){
            intent.putExtra("player","33");
        }
        else if(view == rudiSelect[3]){
            intent.putExtra("player","34");
        }
        else if(view == rudiSelect[4]) {
            intent.putExtra("player", "35");
        }
        else if(view == rudiSelect[5]){
            intent.putExtra("player","36");
        }
        else if(view == rudiSelect[6]){
            intent.putExtra("player","37");
        }
        else if(view == rudiSelect[7]){
            intent.putExtra("player","38");
        }
        else if(view == rudiSelect[8]){
            intent.putExtra("player","39");
        }
        else if(view == rudiSelect[9]){
            intent.putExtra("player","40");
        }
        startActivity(intent);
    }
}

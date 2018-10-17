package com.example.android.drum_1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.android.drum_1.AdapterPackage.RudimentAdapter;
import com.example.android.drum_1.Common.Common;
import com.example.android.drum_1.Model.Rudiment;
import com.example.android.drum_1.R;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class selectRudimentActivity extends AppCompatActivity {


    FeatureCoverFlow coverFlow;
    RudimentAdapter adapter;
    TextSwitcher mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_rudiment);

        initData();
        adapter = new RudimentAdapter(Common.rudimentList,this);

        coverFlow = (FeatureCoverFlow)findViewById(R.id.coverFlow);
        mTitle = (TextSwitcher)findViewById(R.id.mtitle);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(selectRudimentActivity.this);
                TextView text = (TextView)inflater.inflate(R.layout.layout_rudiment,null);
                return text;
            }
        });







        coverFlow.setAdapter(adapter);
        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(Common.rudimentList.get(position).getRudiment());
            }

            @Override
            public void onScrolling() {

            }
        });
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(selectRudimentActivity.this,MainActivity.class);
                if(i==3){
                    Toast.makeText(getApplicationContext(),"개발진행중찡긋",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    intent.putExtra("player",i+1);
                    startActivity(intent);
                }
            }
        });






    }

    private void initData() {
        Rudiment rudiment = new Rudiment("Rudiment 1","https://i.imgur.com/KzwGMSw.png");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 2","https://i.imgur.com/t1kMatS.png");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 3","https://i.imgur.com/pg3XaP6.png");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 4","https://i.imgur.com/iEIEkpq.png");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 5","https://i.imgur.com/Jz64jnv.png");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 6","https://i.imgur.com/FXwiPOT.png");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 7","https://i.imgur.com/2DRZkPZ.png");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 8","https://i.imgur.com/ZJ5HZGQ.png");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 9","https://i.imgur.com/7rbltxZ.png");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 10","https://i.imgur.com/8CAFi5G.png");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 11","https://i.imgur.com/KzwGMSw.png");
        Common.rudimentList.add(rudiment);

    }
}

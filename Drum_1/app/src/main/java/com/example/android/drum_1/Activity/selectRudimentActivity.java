package com.example.android.drum_1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
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
                intent.putExtra("player",i+1);
                startActivity(intent);
            }
        });






    }

    private void initData() {
        Rudiment rudiment = new Rudiment("Rudiment 1","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 2","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 3","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 4","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 5","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 6","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 7","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 8","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 9","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 10","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 11","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 12","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 13","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 14","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 15","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 16","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 17","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 18","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 19","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 20","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 21","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 22","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 23","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 24","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 25","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 26","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 27","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 28","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 29","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 30","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 31","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 32","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 33","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 34","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 35","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 36","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 37","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 38","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);


        rudiment = new Rudiment("Rudiment 39","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);

        rudiment = new Rudiment("Rudiment 40","https://www.audiomart.co.za/images/db-dms141012di-bk.jpg?osCsid=5fc4e44a91adfd520b167abae231e0c8");
        Common.rudimentList.add(rudiment);




    }
}

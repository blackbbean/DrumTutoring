package com.example.android.drum_1.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.drum_1.R;

public class ResultActivity extends Activity {



    private TextView noticeText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result);
        noticeText = (TextView)findViewById(R.id.notice);

        if(MainActivity.scoreInt==-1){
            noticeText.setText("ERROR : 입력갯수 오류");
        }
        else{
            noticeText.setText("Your score is " + MainActivity.scoreInt);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

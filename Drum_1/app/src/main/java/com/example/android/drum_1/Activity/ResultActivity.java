package com.example.android.drum_1.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.drum_1.R;

public class ResultActivity extends Activity {


    ProgressBar loading;
    TextView noticeText;
    public static boolean scoreFlag=false;
    public static int scoreInt;
    ScoreThread st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_result);
        loading = (ProgressBar)findViewById(R.id.showProgress);
        noticeText = (TextView)findViewById(R.id.notice);
        st = new ScoreThread();
        loading.setVisibility(ProgressBar.VISIBLE);
        loading.setIndeterminate(true);
        loading.setMax(100);

        st.start();

    }

    class ScoreThread extends Thread {

        public void run() {
            if(scoreFlag){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loading.setVisibility(ProgressBar.INVISIBLE);
                        noticeText.setText("Your score is " + scoreInt);
                    }
                });

            }

        }
    }


}

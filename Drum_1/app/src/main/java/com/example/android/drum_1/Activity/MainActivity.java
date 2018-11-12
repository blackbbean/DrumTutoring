package com.example.android.drum_1.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiReceiver;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.drum_1.MidiFramer;
import com.example.android.drum_1.MidiOutputPortSelector;
import com.example.android.drum_1.MidiPortSelector;
import com.example.android.drum_1.MidiPortWrapper;
import com.example.android.drum_1.MidiSynth;
import com.example.android.drum_1.MyReceiver;
import com.example.android.drum_1.R;
import com.example.android.drum_1.ScopeLogger;

import org.billthefarmer.mididriver.MidiDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity implements ScopeLogger,MidiDriver.OnMidiStartListener {

    public static boolean aistartFlag;
    MidiManager m;
    MidiReceiver logreceiver;
    private TextView mLog;
    private ScrollView mScroller;
    private static final int MAX_LINES = 100;
    private final LinkedList<String> mLogLines = new LinkedList<>();
    private MidiOutputPortSelector mLogSenderSelector;
    private MidiSynth midiDriver = new MidiSynth();
    //private List<TextView> notes;

    private ImageButton startButton;

    private ImageView drum0;
    private ImageView drum1;
    private ImageView musicSheet;
    AnimationDrawable leftAni;
    AnimationDrawable rightAni;

    private int count;;
    private CountDownTimer countDownTimer;
    private TextView timerText;




    public static long realStartTime;
    public static boolean sendFlag;



    String noteDirection;
    long beat;
    String[] newString;
    public static boolean flag;
    public static long trash;
    public static int currentRudi;
    public static boolean resultFlag;
    public static float scoreInt;;



    //onCreate : 레이아웃 생성,초기화 컴포넌트.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        //ResultThread rt = new ResultThread();
        //t = new Thread(this);
        aistartFlag = false;
        flag=false;
        resultFlag =false;
        scoreInt = -2;
        count = 5;
        newString= null;
        sendFlag = false;

        timerText = (TextView)findViewById(R.id.timer);
        m = (MidiManager) getApplicationContext().getSystemService(Context.MIDI_SERVICE);
        logreceiver = new MyReceiver(this, midiDriver);
        MidiFramer connectFramer = new MidiFramer(logreceiver);
        startButton = (ImageButton) findViewById(R.id.btnPlay);
        musicSheet = (ImageView)findViewById(R.id.musicSheet);
        drum0 = (ImageView)findViewById(R.id.image0);
        drum0.setBackgroundResource(R.drawable.leftdrum_animation);
        drum1 = (ImageView)findViewById(R.id.image1);
        drum1.setBackgroundResource(R.drawable.rightdrum_animation);

        rightAni= (AnimationDrawable)drum1.getBackground();

        leftAni= (AnimationDrawable)drum0.getBackground();




        Intent intent = getIntent();
        int rudimentNo = intent.getIntExtra("player",-1);
        currentRudi =rudimentNo;
        setMusicSheet(currentRudi);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timerText.setVisibility(View.VISIBLE);
                timerFunc();
            }
        });



        mLogSenderSelector = new MidiOutputPortSelector(m, this, R.id.spinner_senders) {
            @Override
            public void onPortSelected(final MidiPortWrapper wrapper) {
                super.onPortSelected(wrapper); //이거 호출 과정 좀 봐야할듯.
                if (wrapper != null) {
                    mLogLines.clear();
                    MidiDeviceInfo deviceInfo = wrapper.getDeviceInfo();
                    if (deviceInfo == null) {
                    } else {

                    }
                }
            }
        };
        //초기화인듯
        mLogSenderSelector.getSender().connect(connectFramer);

        //리스너!
        midiDriver.setOnMidiStartListener(this);
        //점수내는 화면으로 들어가는 ...
        //rt.start();
        new Thread(new Runnable() {
            @Override public void run() {
                while (true) {
                    //System.out.println("checking");
                    if (resultFlag && scoreInt!=-2){
                        Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                        resultFlag=false;
                        startActivity(intent);
                        //finish();
                    }
                }
            }
        }).start();



    }// end of onCreate




    void setMusicSheet(int currentRudi){
        switch (currentRudi){
            case 1:
                musicSheet.setImageResource(R.drawable.rudi1);
                break;
            case 2:
                musicSheet.setImageResource(R.drawable.rudi2);
                break;
            case 3:
                musicSheet.setImageResource(R.drawable.rudi3);
                break;
            case 5:
                musicSheet.setImageResource(R.drawable.rudi5);
                break;
            case 6:
                musicSheet.setImageResource(R.drawable.rudi6);
                break;
            case 7:
                musicSheet.setImageResource(R.drawable.rudi7);
                break;
            case 8:
                musicSheet.setImageResource(R.drawable.rudi8);
                break;
            case 9:
                musicSheet.setImageResource(R.drawable.rudi9);
                break;
            case 10:
                musicSheet.setImageResource(R.drawable.rudi10);
                break;
            case 11:
                musicSheet.setImageResource(R.drawable.rudi11);
                break;

        }
    }


    //rudiment에 대해서 play note가 달라짐
    void PlayMusic() {

        switch (currentRudi) {
            case 1:
                InputStream inputStream = getResources().openRawResource(R.raw.rudi1);
                readNote(inputStream);
                break;
            case 2:
                InputStream inputStream2 = getResources().openRawResource(R.raw.rudi2);
                readNote(inputStream2);
                break;
            case 3:
                InputStream inputStream3 = getResources().openRawResource(R.raw.rudi3);
                readNote(inputStream3);
                break;
            case 5:
                InputStream inputStream5 = getResources().openRawResource(R.raw.rudi5);
                readNote(inputStream5);
                break;
            case 6:
                InputStream inputStream6 = getResources().openRawResource(R.raw.rudi6);
                readNote(inputStream6);
                break;
            case 7:
                InputStream inputStream7 = getResources().openRawResource(R.raw.rudi7);
                readNote(inputStream7);
                break;
            case 8:
                InputStream inputStream8 = getResources().openRawResource(R.raw.rudi8);
                readNote(inputStream8);
                break;
            case 9:
                InputStream inputStream9 = getResources().openRawResource(R.raw.rudi9);
                readNote(inputStream9);
                break;
            case 10:
                InputStream inputStream10 = getResources().openRawResource(R.raw.rudi10);
                readNote(inputStream10);
                break;
            case 11:
                InputStream inputStream11 = getResources().openRawResource(R.raw.rudi11);
                readNote(inputStream11);
                break;
        }
    }//end of function

    //다시 수정해야
    void highlightNote() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<newString.length;i++){
                    String[] single = new String[2];
                    if (newString[i].contains("START")) {
                        continue;
                    } else if (newString[i].contains("END")) {
                        //불들어오는데 초기화해야할듯
                        count=5;
                       break;
                    } else {
                        single = newString[i].split(",");
                        noteDirection = single[1];
                        beat = Long.valueOf(single[0]);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //rightDrumView.setTextColor(Color.BLACK);
                                //leftDrumView.setTextColor(Color.BLACK);
                                if (noteDirection.equals("R")) {
                                   //drum1.setImageResource(R.drawable.r_blue);
                                    rightAni.start();
                                    rightAni.setOneShot(true);

                                } else if (noteDirection.equals("L")) {
                                    leftAni.start();
                                    leftAni.setOneShot(true);

                                }

                            }
                        });

                        //beat = Long.valueOf(single[0]);
                        int temp = (int) ((150 * beat) / 24);
                        try {
                            Thread.sleep(temp);
                            rightAni.stop();
                            leftAni.stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }//end of loop

            }
        }).start();
    }


    void readNote(InputStream inputStream) {
        String line = null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        newString = null;

        StringBuilder sb = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String text = sb.toString();
        newString = text.split(";");
        highlightNote();
    }


    @Override
    protected void onStop() {
        super.onStop();

        //socketIns.close();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        setKeepScreenOn(menu.findItem(R.id.action_keep_screen_on).isChecked());
        return true;
    }

    //keep screen on mode
    private void setKeepScreenOn(boolean keepScreenOn) {
        if (keepScreenOn) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_all:
                mLogLines.clear();
                break;
            case R.id.action_keep_screen_on:
                boolean checked = !item.isChecked();
                setKeepScreenOn(checked);
                item.setChecked(checked);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    protected void onResume() {
        //super.onResume();
        //midiDriver.start();
    }
*/
    /*
    @Override
    protected void onPause() {
        super.onPause();

        //finish();

    }
*/




    public void log(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //logOnUiThread(string);
            }
        });
    }


    @Override
    public void onMidiStart() {
        Log.d(this.getClass().getName(), "onMidiStart()");
    }

    void timerFunc(){
        countDownTimer();
        countDownTimer.start();
    }


    //TODO 이거 왜 0안나오는지 알아내기
    //TODO 한번 플레이버튼 누른 후 타이머 안나오는 이유
    //TODO 깜빡거림 무한루프로 바꾸기
    //TODO 악보랑 루디먼트 음원이랑 좀 다른거 논의
    //
    public void countDownTimer(){
        countDownTimer = new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long l) {

             timerText.setText(String.valueOf(count));
             count--;


            }

            @Override
            public void onFinish() {
                timerText.setVisibility(View.INVISIBLE);
                realStartTime = System.nanoTime();
                trash = realStartTime;
                aistartFlag = true;
                sendFlag = true;
                PlayMusic();
            }
        };
    }




}

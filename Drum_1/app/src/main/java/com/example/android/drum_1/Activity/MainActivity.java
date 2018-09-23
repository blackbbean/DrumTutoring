package com.example.android.drum_1.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiReceiver;
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
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.drum_1.MidiFramer;
import com.example.android.drum_1.MidiOutputPortSelector;
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

    MidiManager m;
    MidiReceiver logreceiver;
    private TextView mLog;
    private ScrollView mScroller;
    private static final int MAX_LINES = 100;
    private final LinkedList<String> mLogLines = new LinkedList<>();
    private MidiOutputPortSelector mLogSenderSelector;
    private MidiSynth midiDriver = new MidiSynth();
    //private List<TextView> notes;

    private Button startButton;

    private ImageView drum0;
    private ImageView drum1;
    private ImageView musicSheet;
    AnimationDrawable leftAni;
    AnimationDrawable rightAni;


    private ImageView drum15;
    public static long realStartTime;



    /*
    private String ip = "172.30.1.17";
    private int port = 9999;
    private Socket socket;
    public static BufferedWriter networkWriter;
    */


    String noteDirection;
    long beat;
    String[] newString = null;
    public static boolean flag;
    public static long trash;
    public static int currentRudi;
    public static boolean resultFlag = false;
    ResultThread rt;


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

        rt = new ResultThread();
        //t = new Thread(this);

        m = (MidiManager) getApplicationContext().getSystemService(Context.MIDI_SERVICE);
        logreceiver = new MyReceiver(this, midiDriver);
        MidiFramer connectFramer = new MidiFramer(logreceiver);
        startButton = (Button) findViewById(R.id.btnPlay);
        musicSheet = (ImageView)findViewById(R.id.musicSheet);
        drum0 = (ImageView)findViewById(R.id.image0);
        drum0.setBackgroundResource(R.drawable.leftdrum_animation);
        drum1 = (ImageView)findViewById(R.id.image1);
        drum1.setBackgroundResource(R.drawable.rightdrum_animation);

        rightAni= (AnimationDrawable)drum1.getBackground();

        leftAni= (AnimationDrawable)drum0.getBackground();


        /*
        leftDrumView = (ImageView) findViewById(R.id.leftImage);
        rightDrumView = (ImageView) findViewById(R.id.rightImage);
        */


        Intent intent = getIntent();

        int rudimentNo = intent.getIntExtra("player",-1);
        currentRudi =rudimentNo;
        setMusicSheet(currentRudi);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realStartTime = System.nanoTime();
                flag = false;
                trash = realStartTime;
                PlayMusic();
            }
        });


        //mLog = (TextView) findViewById(R.id.log);
        //mScroller = (ScrollView) findViewById(R.id.scroll);
        //MidiManager midiManager, Activity activity, int spinnerId
        mLogSenderSelector = new MidiOutputPortSelector(m, this, R.id.spinner_senders) {
            @Override
            public void onPortSelected(final MidiPortWrapper wrapper) {
                super.onPortSelected(wrapper); //이거 호출 과정 좀 봐야할듯.
                if (wrapper != null) {
                    mLogLines.clear();
                    MidiDeviceInfo deviceInfo = wrapper.getDeviceInfo();
                    if (deviceInfo == null) {
                        //log(getString(R.string.header_text));
                    } else {
                        //log(MidiPrinter.formatDeviceInfo(deviceInfo));
                        Toast.makeText(getApplicationContext(), "Drum Connected", Toast.LENGTH_SHORT).show();


                        //server 통신
                    }
                }
            }
        };
        //초기화인듯
        mLogSenderSelector.getSender().connect(connectFramer);

        //리스너!
        midiDriver.setOnMidiStartListener(this);
        rt.start();

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


        //TODO 루디먼트별~~~
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
                for (int i = 0; i < newString.length; i++) {
                    String[] single = new String[2];
                    if (newString[i].contains("START")) {
                        continue;
                    } else if (newString[i].contains("END")) {
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

                    /*
                    try {
                        int temp = (int) ((150 * beat) / 24);
                        Thread.sleep(temp);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    */
                }//end of for loop

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

    @Override
    protected void onResume() {
        super.onResume();
        midiDriver.start();
    }


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


    /*
    @Override
    public void run() {
        try {
            socket = new Socket(ip,port);
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Log.i("mainActivity","socket connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
//end of main activity class

    class ResultThread extends Thread {
        public void run() {
            while (true) {
                if (ResultActivity.scoreFlag){
                    ResultActivity.scoreFlag = false;
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(intent);
                }
            }
        }
    }

}

package com.example.android.drum_1;

import android.media.midi.MidiReceiver;
import android.util.Log;

import com.example.android.drum_1.Activity.MainActivity;
import com.example.android.drum_1.Activity.MenuActivity;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Minjeong Kim on 2018-05-20.
 */

public class MyReceiver extends MidiReceiver {
    public static final String TAG = "MidiScope";
    private static final long MILLIS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
    public long mStartTime;
    private ScopeLogger mLogger;
    private MidiSynth midiDriver;
    public static byte velocity;
    public static byte handflag;



    public MyReceiver(ScopeLogger logger,MidiSynth driver) {
        mStartTime = System.nanoTime();
        mLogger = logger;
        midiDriver = driver;
    }

    /*
    public void changeTime(){
        mStartTime = System.nanoTime();
    }
    *./

    /*
     * @see android.media.midi.MidiReceiver#onReceive(byte[], int, int, long)
     */
    @Override
    public void onSend(byte[] data, int offset, int count, long timestamp)
            throws IOException {
        double seconds = 0;
        StringBuilder sb = new StringBuilder();
        boolean startFlag = false;
        if (timestamp == 0) {
            sb.append("-----0----: ");
        } else {
            //시간추가
            long monoTime;
            if(!MainActivity.flag) {
                monoTime= timestamp - MainActivity.realStartTime;
                MainActivity.flag = true;
                startFlag = true;
            }
            else {
                monoTime = timestamp - MainActivity.trash;
            }
            MainActivity.trash = timestamp;
                seconds = (double) monoTime / MILLIS_PER_SECOND * 100;
            sb.append(String.format(Locale.US, "%10.3f: ", seconds));
        }
        //이거 잘 모르겠음
        sb.append(MidiPrinter.formatBytes(data, offset, count));
        sb.append(": ");
        //note on channel/note/velocity
        sb.append(MidiPrinter.formatMessage(data, offset));
        String text = sb.toString();
        //최종 나타나는부분 .
        //mLogger.log(text);
        Log.i(TAG, text);



        String sendData = "";
        if(velocity!=0){
            if(handflag==1){
                sendData += "L";
            }
            else if(handflag==2) {
                sendData += "R";
            }
            else {
                sendData = "AIFINISHED";
                MainActivity.flag = false;

            }
            if(handflag!=3) {
                if (velocity > 150)
                    sendData += "a";
                else if (velocity > 70)
                    sendData += "b";
                else
                    sendData += "c";

                sendData += seconds;
            }

            //PrintWriter out = new PrintWriter(NetworkTask.networkWriter,true);
            if(startFlag){
                String temp = String.valueOf(MainActivity.currentRudi);
                MenuActivity.socketIns.sendFunc("AISTART"+temp);
                //out.println("AISTART"+temp);
            }
            MenuActivity.socketIns.sendFunc(sendData);

            //out.println(sendData);
            if(sendData.contains("AIFINISHED")){
                MainActivity.resultFlag = true;
            }
        }


        //가공해야함.
        midiDriver.playNote(data);
    }
}

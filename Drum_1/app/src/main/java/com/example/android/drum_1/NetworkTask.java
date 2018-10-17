package com.example.android.drum_1;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.drum_1.Activity.MainActivity;
import com.example.android.drum_1.Activity.ResultActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Minjeong Kim on 2018-08-25.
 */


public class NetworkTask extends AsyncTask<Void, Void, Void> {


    String dstAddress;
    int dstPort;
    public static BufferedWriter networkWriter;
    PrintWriter out;


    public NetworkTask(String addr, int port) {
        dstAddress = addr;
        dstPort = port;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket = new Socket(dstAddress,dstPort);
            Log.i("NetworkTask","socket connected");
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            InputStream inputStream = socket.getInputStream();


            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String receivedData = new String(buffer,0,bytesRead,"UTF-8");
                Log.i("NetworkTask","ReceivedData  =   " + receivedData);
                MainActivity.scoreInt = Float.valueOf(receivedData);
                if(isDigit(receivedData)){
                    MainActivity.resultFlag = true;
                }
                buffer = new byte[1024];

            }

            Log.i("NetworkTask","out of loop");
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    void sendFunc(String text){
        out = new PrintWriter(networkWriter,true);
        out.println(text);
    }

    boolean isDigit(String text){
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c))
                return false;
        }
        return true;
    }
}

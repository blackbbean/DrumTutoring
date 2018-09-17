package com.example.android.drum_1;

import android.media.midi.MidiDeviceInfo;
import android.os.Bundle;

/**
 * Created by Minjeong Kim on 2018-05-20.
 */
public class MidiPrinter {

    public static final String[] CHANNEL_COMMAND_NAMES = { "NoteOff", "NoteOn",
            "PolyTouch", "Control", "Program", "Pressure", "Bend" };
    public static final String[] SYSTEM_COMMAND_NAMES = { "SysEx", // F0
            "TimeCode",    // F1
            "SongPos",     // F2
            "SongSel",     // F3
            "F4",          // F4
            "F5",          // F5
            "TuneReq",     // F6
            "EndSysex",    // F7
            "TimingClock", // F8
            "F9",          // F9
            "Start",       // FA
            "Continue",    // FB
            "Stop",        // FC
            "FD",          // FD
            "ActiveSensing", // FE
            "Reset"        // FF
    };

    public static String getName(int status) { // midi header 출력부분
        if (status >= 0xF0) { //over 240
            int index = status & 0x0F;
            return SYSTEM_COMMAND_NAMES[index];
        } else if (status >= 0x80) { //over 128
            int index = (status >> 4) & 0x07; // 1001 1000 -> 1001 , 9& 7 = 1
            return CHANNEL_COMMAND_NAMES[index];
        } else {
            return "data";
        }
    }

    //이부분 잘 모르겠음
    public static String formatBytes(byte[] data, int offset, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(String.format(" %02X", data[offset + i]));
        }
        return sb.toString();
    }

    public static String formatMessage(byte[] data, int offset) {
        StringBuilder sb = new StringBuilder();
        byte statusByte = data[offset++];
        int status = statusByte & 0xFF;
        sb.append(getName(status)).append("(");
        int numData = MidiConstants.getBytesPerMessage(statusByte) - 1;
        if ((status >= 0x80) && (status < 0xF0)) { // channel message
            int channel = status & 0x0F;
            // Add 1 for humans who think channels are numbered 1-16.
            sb.append((channel + 1)).append(", ");
        }
        for (int i = 0; i < numData; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(data[offset++]);
            if(i==0){
                if(data[offset-1]== 50) //왼쪽 50
                    MyReceiver.handflag = 1;
                else if(data[offset-1]==40) //오른쪽 40
                    MyReceiver.handflag = 2;
                else{//종료
                    MyReceiver.handflag = 3;
                }
            }
            else if(i==1 && data[offset-1]!=0){
                MyReceiver.velocity = data[offset-1];
            }
            else
                MyReceiver.velocity = 0;
        }
        sb.append(")");
        return sb.toString();
    }

    /*
    public static String formatDeviceInfo(MidiDeviceInfo info) {
        StringBuilder sb = new StringBuilder();
        if (info != null) {
            Bundle properties = info.getProperties();
            for (String key : properties.keySet()) {
                Object value = properties.get(key);
                sb.append(key).append(" = ").append(value).append('\n');
            }
            for (MidiDeviceInfo.PortInfo port : info.getPorts()) {
                sb.append((port.getType() == MidiDeviceInfo.PortInfo.TYPE_INPUT) ? "input" : "output")
                        .append("[")
                        .append(port.getPortNumber())
                        .append("] = \"")
                        .append(port.getName()).append("\"\n");
            }
        }
        return sb.toString();
    }
    */
}

package com.example.android.drum_1;

import android.app.Activity;
import android.media.midi.MidiDevice;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiManager;
import android.media.midi.MidiOutputPort;
import android.media.midi.MidiSender;
import android.util.Log;

import com.example.android.drum_1.Common.Common;

import java.io.IOException;

/**
 * Created by Minjeong Kim on 2018-05-20.
 */

public class MidiOutputPortSelector extends MidiPortSelector {
    private MidiOutputPort mOutputPort;
    private MidiDispatcher mDispatcher = new MidiDispatcher();
    private MidiDevice mOpenDevice;



    public MidiOutputPortSelector(MidiManager midiManager, Activity activity,
                                  int spinnerId) {
        super(midiManager, activity, spinnerId, MidiDeviceInfo.PortInfo.TYPE_OUTPUT);
    }

    //interface(MidiPortSelector.java) 구현
    @Override
    public void onPortSelected(final MidiPortWrapper wrapper) {
        Log.i(MidiConstants.TAG, "onPortSelected: " + wrapper);
        //later
        close();

        final MidiDeviceInfo info = wrapper.getDeviceInfo();
        if (info != null) {
            mMidiManager.openDevice(info, new MidiManager.OnDeviceOpenedListener() {
                @Override
                public void onDeviceOpened(MidiDevice device) {
                    if (device == null) {
                        Log.e(MidiConstants.TAG, "could not open " + info);
                    } else {
                        mOpenDevice = device;
                        mOutputPort = device.openOutputPort(wrapper.getPortIndex());
                        if (mOutputPort == null) {
                            Log.e(MidiConstants.TAG,
                                    "could not open output port for " + info);
                            return;
                        }
                        //이쪽에서 연결 통신 이루어지는듯?
                        if(Common.connectFlag==false){
                            Common.connectFlag=true;
                            mOutputPort.connect(mDispatcher);
                        }
                    }
                }
            }, null);
            // Don't run the callback on the UI thread because openOutputPort might take a while.
        }
    }

    @Override
    public void onClose() {
        try {
            if (mOutputPort != null) {
                Common.connectFlag=false;
                mOutputPort.disconnect(mDispatcher);
            }
            mOutputPort = null;
            if (mOpenDevice != null) {
                mOpenDevice.close();
            }
            mOpenDevice = null;
        } catch (IOException e) {
            Log.e(MidiConstants.TAG, "cleanup failed", e);
        }
    }

    /**
     * You can connect your MidiReceivers to this sender. The user will then select which output
     * port will send messages through this MidiSender.
     * @return a MidiSender that will send the messages from the selected port.
     */
    public MidiSender getSender() {
        return mDispatcher.getSender();
    }

}
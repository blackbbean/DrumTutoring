package com.example.android.drum_1;

import android.app.Activity;
import android.media.midi.MidiDeviceInfo;
import android.media.midi.MidiDeviceStatus;
import android.media.midi.MidiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashSet;

/**
 * Created by Minjeong Kim on 2018-05-20.
 */

//여긴 별로 안봐도될듯


//함수의 호출 실행 시점을 프로그래머가 아닌 시스템에서 결정하는 함수는 (CALLBACK)콜백 함수라 부른다
//Callback class used for clients to receive MIDI device added and removed notifications
public abstract class MidiPortSelector extends MidiManager.DeviceCallback {
    private int mType = MidiDeviceInfo.PortInfo.TYPE_INPUT; //input으로 애초에 고정.
    //ArrayAdapter : 리스트뷰
    protected ArrayAdapter<MidiPortWrapper> mAdapter;
    //포트 정보 제공하는 hashSet.
    protected HashSet<MidiPortWrapper> mBusyPorts = new HashSet<MidiPortWrapper>();
    private Spinner mSpinner;
    protected MidiManager mMidiManager;
    protected Activity mActivity;
    private MidiPortWrapper mCurrentWrapper;
    public static boolean connectFlag = false;

    /**
     * @param midiManager
     * @param activity
     * @param spinnerId
     *            ID from the layout resource
     * @param type
     *            TYPE_INPUT or TYPE_OUTPUT
     */
    public MidiPortSelector(MidiManager midiManager, Activity activity,
                            int spinnerId, int type) {
        mMidiManager = midiManager;
        mActivity = activity;
        mType = type;
        //adapter 객체 할당.
        mAdapter = new ArrayAdapter<MidiPortWrapper>(activity,
                android.R.layout.simple_spinner_item);
        // dropdown 되었을 때 보여지는 View에서 사용될 layout을 별도로 설정하는 경우 사용
        mAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        mAdapter.add(new MidiPortWrapper(null, 0, 0));

        mSpinner = (Spinner) activity.findViewById(spinnerId);
        mSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        mCurrentWrapper = mAdapter.getItem(pos);
                        //여기서!!!
                        //if(!connectFlag && mCurrentWrapper.getmString()!="- - - - - -"){
                        //connectFlag=true;
                        //MidiOutputPortSelector
                        onPortSelected(mCurrentWrapper);

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        onPortSelected(null);
                        mCurrentWrapper = null;
                    }
                });
        mSpinner.setAdapter(mAdapter);

        mMidiManager.registerDeviceCallback(this,
                new Handler(Looper.getMainLooper()));

        MidiDeviceInfo[] infos = mMidiManager.getDevices();
        for (MidiDeviceInfo info : infos) {
            onDeviceAdded(info);
        }
    }

    /**
     * Set to no port selected.
     */
    public void clearSelection() {
        mSpinner.setSelection(0);
    }

    private int getInfoPortCount(final MidiDeviceInfo info) {
        int portCount = (mType == MidiDeviceInfo.PortInfo.TYPE_INPUT)
                ? info.getInputPortCount() : info.getOutputPortCount();
        return portCount;
    }

    @Override
    public void onDeviceAdded(final MidiDeviceInfo info) {
        int portCount = getInfoPortCount(info);
        for (int i = 0; i < portCount; ++i) {
            MidiPortWrapper wrapper = new MidiPortWrapper(info, mType, i);
            mAdapter.add(wrapper);
            Log.i(MidiConstants.TAG, wrapper + " was added");
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDeviceRemoved(final MidiDeviceInfo info) {
        int portCount = getInfoPortCount(info);
        for (int i = 0; i < portCount; ++i) {
            MidiPortWrapper wrapper = new MidiPortWrapper(info, mType, i);
            MidiPortWrapper currentWrapper = mCurrentWrapper;
            mAdapter.remove(wrapper);
            // If the currently selected port was removed then select no port.
            if (wrapper.equals(currentWrapper)) {
                clearSelection();
            }
            mAdapter.notifyDataSetChanged();
            //connectFlag=false;
            Log.i(MidiConstants.TAG, wrapper + " was removed");
        }
    }

    @Override
    public void onDeviceStatusChanged(final MidiDeviceStatus status) {
        // If an input port becomes busy then remove it from the menu.
        // If it becomes free then add it back to the menu.
        if (mType == MidiDeviceInfo.PortInfo.TYPE_INPUT) {
            MidiDeviceInfo info = status.getDeviceInfo();
            Log.i(MidiConstants.TAG, "MidiPortSelector.onDeviceStatusChanged status = " + status
                    + ", mType = " + mType
                    + ", activity = " + mActivity.getPackageName()
                    + ", info = " + info);
            // Look for transitions from free to busy.
            int portCount = info.getInputPortCount();
            for (int i = 0; i < portCount; ++i) {
                MidiPortWrapper wrapper = new MidiPortWrapper(info, mType, i);
                if (!wrapper.equals(mCurrentWrapper)) {
                    if (status.isInputPortOpen(i)) { // busy?
                        if (!mBusyPorts.contains(wrapper)) {
                            // was free, now busy
                            mBusyPorts.add(wrapper);
                            mAdapter.remove(wrapper);
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        if (mBusyPorts.remove(wrapper)) {
                            // was busy, now free
                            mAdapter.add(wrapper);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }

    /**
     * Implement this method to handle the user selecting a port on a device.
     *
     * @param wrapper
     */
    public abstract void onPortSelected(MidiPortWrapper wrapper);

    /**
     * Implement this method to clean up any open resources.
     */
    public abstract void onClose();

    /**
     *
     */
    public void close() {
        onClose();
    }
}

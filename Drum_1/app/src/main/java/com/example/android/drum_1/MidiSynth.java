package com.example.android.drum_1;

import org.billthefarmer.mididriver.MidiDriver;

/**
 * Created by Minjeong Kim on 2018-05-21.
 */

public class MidiSynth extends MidiDriver {
    void playNote(byte[] temp){
        write(temp);
    }
}

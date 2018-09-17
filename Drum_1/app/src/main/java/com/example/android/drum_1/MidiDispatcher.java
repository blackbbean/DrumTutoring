package com.example.android.drum_1;

import android.media.midi.MidiReceiver;
import android.media.midi.MidiSender;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Minjeong Kim on 2018-05-20.
 */

public final class MidiDispatcher extends MidiReceiver {

    //걍 일종의 arrayList
    private final CopyOnWriteArrayList<MidiReceiver> mReceivers
            = new CopyOnWriteArrayList<MidiReceiver>();

    private final MidiSender mSender = new MidiSender() {
        /**
         * Called to connect a {@link MidiReceiver} to the sender
         *
         * @param receiver the receiver to connect
         */
        @Override
        public void onConnect(MidiReceiver receiver) {
            mReceivers.add(receiver);
        }

        /**
         * Called to disconnect a {@link MidiReceiver} from the sender
         *
         * @param receiver the receiver to disconnect
         */
        @Override
        public void onDisconnect(MidiReceiver receiver) {
            mReceivers.remove(receiver);
        }
    };

    /**
     * Returns the number of {@link MidiReceiver}s this dispatcher contains.
     * @return the number of receivers
     */
    public int getReceiverCount() {
        return mReceivers.size();
    }

    /**
     * Returns a {@link MidiSender} which is used to add and remove
     * {@link MidiReceiver}s
     * to the dispatcher's receiver list.
     * @return the dispatcher's MidiSender
     */
    public MidiSender getSender() {
        return mSender;
    }

    @Override
    public void onSend(byte[] msg, int offset, int count, long timestamp) throws IOException {
        for (MidiReceiver receiver : mReceivers) {
            try {
                receiver.send(msg, offset, count, timestamp);
            } catch (IOException e) {
                // if the receiver fails we remove the receiver but do not propagate the exception
                mReceivers.remove(receiver);
            }
        }
    }

    @Override
    public void flush() throws IOException {
        for (MidiReceiver receiver : mReceivers) {
            receiver.flush();
        }
    }
}

package com.skylandertech.www.infrademo.utils;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by tangj on 2018/3/28.
 */

public class PlaySignal {

    private final int SAMPLE_RATE = 44100;

    private byte[] genSignal;
    private byte[] genSpace;
    private AudioTrack audioTrack;
    private int buffSize;
    public PlaySignal(final int frequency) {
        buffSize = AudioTrack.getMinBufferSize(SAMPLE_RATE,
                AudioFormat.CHANNEL_CONFIGURATION_STEREO,
                AudioFormat.ENCODING_PCM_16BIT) * 4;

        genSignal = new byte[buffSize];
        genSpace = new byte[buffSize];

        for (int j = 0; j < buffSize;) {
            double dVal = Math.sin(2 * Math.PI * ((double)j)/4.0
                    / (((double)SAMPLE_RATE) / ((double)frequency)));
            final short val = (short) ((dVal * 32767));
            final short val_minus = (short) -val;
            // in 16 bit wav PCM, first byte is the low order byte
            genSpace[j] = 0;
            genSignal[j++] = (byte) (val & 0x00ff);
            genSpace[j] = 0;
            genSignal[j++] = (byte) ((val & 0xff00) >>> 8);
            genSpace[j] = 0;
            genSignal[j++] = (byte) (val_minus & 0x00ff);
            genSpace[j] = 0;
            genSignal[j++] = (byte) ((val_minus & 0xff00) >>> 8);
        }

        audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                SAMPLE_RATE, AudioFormat.CHANNEL_CONFIGURATION_STEREO,
                AudioFormat.ENCODING_PCM_16BIT, buffSize,
                AudioTrack.MODE_STREAM);
        audioTrack.play();
    }

    public void play(final ArrayList<Integer> SignalSpaceList) {
        boolean signal = true;
        int count=0;
        for (Integer d : SignalSpaceList) {
            final int stop = (int) (((double) (d * SAMPLE_RATE)) / 1000000.0) *4 ;
            if (signal)
                for (int i = 0; i < stop;)
                {
                    if (stop - i < buffSize)
                        count= audioTrack.write(genSignal, 0, stop - i);
                    else
                        count = audioTrack.write(genSignal, 0, buffSize);
                    if(count>0)
                        i+=count;
                }
            else
                for (int i = 0; i < stop;)
                {
                    if (stop - i < buffSize)
                        count= audioTrack.write(genSpace, 0, stop - i);
                    else
                        count = audioTrack.write(genSpace, 0, buffSize);
                    if(count>0)
                        i+=count;
                }
            signal = !signal;
        }
    }

}

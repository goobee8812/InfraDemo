package com.skylandertech.www.infrademo.utils;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.Date;

/**
 * Created by jim on 2018/3/20.
 */

public class SineWaveOut {
    private final int SAMPLE_RATE = 44100;
    //信号频率
    private final int SIGNAL_FREQ = 38000;//载波频率
    private final int CHANNEL_CONFIG = AudioFormat.CHANNEL_OUT_STEREO;
    private final int SAMPLE_CONFIG = AudioFormat.ENCODING_PCM_16BIT;
    private SineAudioTrackThread sineAudioTrackThread;
    private boolean isRunning = false;
    private AudioTrack sineAudioTrack;
    public SineWaveOut() {
        int bufferSize = AudioTrack.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, SAMPLE_CONFIG);
        sineAudioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, SAMPLE_RATE, CHANNEL_CONFIG, SAMPLE_CONFIG,
                bufferSize * 4, AudioTrack.MODE_STREAM);
        sineAudioTrack.setStereoVolume(1.0f, 1.0f);
        //默认一直开启
        sineAudioTrack.play();
    }

    public void playSineWave() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                sendMsg(EsCodeCommand.on_data);
//            }
//        }).start();
        sineAudioTrackThread = new SineAudioTrackThread();
        sineAudioTrackThread.start();
    }

    public void closeSineWave() {
        if (sineAudioTrack != null) {
            if (!SineAudioTrackThread.interrupted()) {
                isRunning = false;
            }
        }
    }

    private synchronized void sendMsg(int[] arr){
        short Amp = Short.MAX_VALUE;
        short[] bitDataSine = new short[SAMPLE_RATE * 2];
        double x = 2.0 * Math.PI * SIGNAL_FREQ / SAMPLE_RATE;
        int dataEnd = 0;
        int temp = 0;

        for (int i = 0; i < bitDataSine.length; i++) {
            bitDataSine[i] = 0;
        }
        for (int j = 0; j < arr.length; j++) {
            for (int index = 0; index < (int) ((float) arr[j] / 1000000 * SAMPLE_RATE); index++) {
                if (j % 2 == 0) {
                    bitDataSine[(dataEnd + index) * 2] = (short) (Amp * Math.sin(x * index));
                    bitDataSine[(dataEnd + index) * 2 + 1] = (short) (-Amp * Math.sin(x * index));
                } else {
                    bitDataSine[(dataEnd + index) * 2] = 0;
                    bitDataSine[(dataEnd + index) * 2 + 1] = 0;
                }
                temp = index;
            }
            dataEnd = dataEnd + temp;
        }
        sineAudioTrack.write(bitDataSine, 0, 2205);
        //延时74ms
//        Looper.prepare();
//        final short[] tempArr = (short[])bitDataSine.clone();
//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                //execute the task
//                sineAudioTrack.write(tempArr, 0, 2205);
//            }
//        }, 75);
//        Looper.loop();
    }

    class SineAudioTrackThread extends Thread {
        private short Amp = Short.MAX_VALUE;
        private short[] bitDataSine = new short[SAMPLE_RATE * 2]; //new short[3500]; //
        int[] pattern = {4500, 4500,
                560, 1690, 560, 1690, 560, 1690, 560, 560,
                560, 560, 560, 560, 560, 560, 560, 560,
                560, 1690, 560, 1690, 560, 1690, 560, 560,
                560, 560, 560, 560, 560, 560, 560, 560,
                560, 1690, 560, 560, 560, 560, 560, 560,
                560, 560, 560, 1690, 560, 1690, 560, 560,
                560, 560, 560, 1690, 560, 1690, 560, 1690,
                560, 1690, 560, 560, 560, 560, 560, 1690,
                560};
        /*  其中公式为：y(t) = A * sin (2πft + φ)
         * A: 振幅，这里为1；
         * f: 频率，这里为freqOfTone;
         * t: 时间，这里为(i/sampleRate);
         * φ: 初相位，这里为0；
         */
        private double x = 2.0 * Math.PI * SIGNAL_FREQ / SAMPLE_RATE;

        @Override
        public synchronized void run() {
            isRunning = true;
            int dataEnd = 0;
            int temp = 0;


            for (int i = 0; i < bitDataSine.length; i++) {
                bitDataSine[i] = 0;
            }
            for (int j = 0; j < EsCodeCommand.on_ifr_data.length; j++) {
                for (int index = 0; index < (int) ((float) EsCodeCommand.on_ifr_data[j] / 1000000 * SAMPLE_RATE); index++) {
                    if (j % 2 != 0) {
                        bitDataSine[(dataEnd + index) * 2] = (short) (Amp * Math.sin(x * index));
                        bitDataSine[(dataEnd + index) * 2 + 1] = (short) (-Amp * Math.sin(x * index));
                    } else {
                        bitDataSine[(dataEnd + index) * 2] = 0;
                        bitDataSine[(dataEnd + index) * 2 + 1] = 0;
                    }
                    temp = index;
                }
                dataEnd = dataEnd + temp;
            }

//            do {
                Log.d("Message", "run--1: " + new Date(System.currentTimeMillis()));
                    sineAudioTrack.write(bitDataSine, 0, bitDataSine.length);//bitDataSine.length);
                Log.d("Message", "run--2: " + new Date(System.currentTimeMillis()));
//            } while (isRunning);
            super.run();
        }
    }
}

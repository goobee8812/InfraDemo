package com.skylandertech.www.infrademo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.skylandertech.www.infrademo.utils.EsCodeCommand;
import com.skylandertech.www.infrademo.utils.PlaySignal;
import com.skylandertech.www.infrademo.utils.SineWaveOut;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Button buttonPlaySineWave;
    private Button buttonStopSineWave;
    private SineWaveOut sineWaveOut;
    private PlaySignal playSignal;
    private Integer[] a = toIntegerArray(EsCodeCommand.on_data);
    private ArrayList<Integer> arr = new ArrayList<> (Arrays.asList(a));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //自检权限是否申请
        if (ContextCompat.checkSelfPermission(MainActivity.this,permissions[0]) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permissions[0]},1);
        }
    }

    private void init() {
        Button psLocalInfra = findViewById(R.id.local_infra_ps);
        psLocalInfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PsLocalActivity.class);
                startActivity(intent);
            }
        });
        Button esLocalInfra = findViewById(R.id.local_infra_es);
        esLocalInfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EsLocalActivity.class);
                startActivity(intent);
            }
        });
        Button atLocalInfra = findViewById(R.id.local_infra_at);
        atLocalInfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AtLocalActivity.class);
                startActivity(intent);
            }
        });
        Button dmLocalInfra = findViewById(R.id.local_infra_dm);
        dmLocalInfra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DmLocalActivity.class);
                startActivity(intent);
            }
        });

        buttonPlaySineWave = findViewById(R.id.button_play_sine_wave);
        buttonStopSineWave = findViewById(R.id.button_stop);
        buttonPlaySineWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sineWaveOut == null) {
                    sineWaveOut = new SineWaveOut();
                }
                sineWaveOut.playSineWave();
            }
        });
        buttonStopSineWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sineWaveOut != null) {
                    sineWaveOut.closeSineWave();
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (sineWaveOut != null) {
                sineWaveOut.closeSineWave();
                sineWaveOut = null;
            }
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    public static Integer[] toIntegerArray(int[] arr){
        int n = arr.length;
        Integer[] iarr=new Integer[n];
        for(int i=0;i<n;i++){
            iarr[i]=new Integer(arr[i]);
        }
        return iarr;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                }else {
                    Toast.makeText(MainActivity.this,"You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

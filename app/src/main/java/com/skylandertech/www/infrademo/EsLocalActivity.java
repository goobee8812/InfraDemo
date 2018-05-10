package com.skylandertech.www.infrademo;

import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.skylandertech.www.infrademo.utils.EsCodeCommand;
import com.skylandertech.www.infrademo.utils.PsCodeCommand;

public class EsLocalActivity extends AppCompatActivity implements View.OnClickListener{
    //获取红外控制类
    private ConsumerIrManager IR;
    //判断是否有红外功能
    boolean IRBack;
    private Button btn_on;
    private Button btn_standby;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_es_local);
        inItEvent();
        initViews();
    }

    private void initViews() {
        btn_on = findViewById(R.id.btn_on);
        btn_standby = findViewById(R.id.btn_standby);
        btn_on.setOnClickListener(this);
        btn_standby.setOnClickListener(this);
    }
    //初始化事务
    private void inItEvent() {
        //获取ConsumerIrManager实例
        IR = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        //如果sdk版本大于4.4才进行是否有红外的功能（手机的android版本）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            IRBack = IR.hasIrEmitter();
            if (!IRBack) {
                Toast.makeText(this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this,"红外设备就绪",Toast.LENGTH_SHORT).show();//可进行下一步操作
            }
        }
    }

    /**
     * 发射红外信号
     *
     * @param carrierFrequency 红外传输的频率，一般的遥控板都是38KHz
     * @param pattern          指以微秒为单位的红外开和关的交替时间 0/1
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private synchronized void sendMsg(final int carrierFrequency, final int[] pattern) {
        IR.transmit(carrierFrequency, pattern);
        //延时74ms
        new Handler().postDelayed(new Runnable(){
            public void run() {
                //execute the task
                IR.transmit(carrierFrequency, pattern);
            }
        }, 75);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_on:
                if (IRBack) {
                    sendMsg(38000, EsCodeCommand.on_data);
                } else {
                    Toast.makeText(EsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_standby:
                if (IRBack) {
                    sendMsg(38000, EsCodeCommand.standby_data);
                } else {
                    Toast.makeText(EsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

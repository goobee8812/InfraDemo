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

import com.skylandertech.www.infrademo.utils.PsCodeCommand;

public class PsLocalActivity extends AppCompatActivity implements View.OnClickListener{
    //获取红外控制类
    private ConsumerIrManager IR;
    //判断是否有红外功能
    boolean IRBack;
    private Button btn_test;
    private Button btn_up;
    private Button btn_down;
    private Button btn_left;
    private Button btn_right;
    private Button btn_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps_local);
        inItEvent();
        initViews();
    }

    private void initViews() {
        btn_test = findViewById(R.id.btn_test);
        btn_up = findViewById(R.id.btn_up);
        btn_down = findViewById(R.id.btn_down);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_done = findViewById(R.id.btn_done);
        btn_test.setOnClickListener(this);
        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_done.setOnClickListener(this);
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
        }, 72);
    }

    /**
     * 更改指令数据 需要修改部分是 82到113位置
     * @param arr
     */
    private void changeParm(int[] arr){
        for (int i=82;i<114;i++){
            PsCodeCommand.baseArr[i] = arr[i-82];
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_test:
                //改变参数
                changeParm(PsCodeCommand.test_data);
                if (IRBack) {
                    sendMsg(38000, PsCodeCommand.baseArr);
                } else {
                    Toast.makeText(PsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_up:
                //改变参数
                changeParm(PsCodeCommand.up_data);
                if (IRBack) {
                    sendMsg(38000, PsCodeCommand.baseArr);
                } else {
                    Toast.makeText(PsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_down:
                //改变参数
                changeParm(PsCodeCommand.down_data);
                if (IRBack) {
                    sendMsg(38000, PsCodeCommand.baseArr);
                } else {
                    Toast.makeText(PsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_left:
                //改变参数
                changeParm(PsCodeCommand.left_data);
                if (IRBack) {
                    sendMsg(38000, PsCodeCommand.baseArr);
                } else {
                    Toast.makeText(PsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_right:
                //改变参数
                changeParm(PsCodeCommand.right_data);
                if (IRBack) {
                    sendMsg(38000, PsCodeCommand.baseArr);
                } else {
                    Toast.makeText(PsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_done:
                //改变参数
                changeParm(PsCodeCommand.done_data);
                if (IRBack) {
                    sendMsg(38000, PsCodeCommand.baseArr);
                } else {
                    Toast.makeText(PsLocalActivity.this,"对不起，该设备上没有红外功能!",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}

package com.skylandertech.www.infrademo.utils;

/**  光峰遥控器
 * Created by tangj on 2018/3/27.
 */

public class AtCodeCommand {
    //编码规则
    //起始码S电平宽度为：9000us低电平+450us高电平
    public static final int startdown = 9000;
    public static final int startup = 4500;

    //数据码由0，1组成：
    //0的电平宽度为：600us低电平+600us高电平，
    public static final int zerodown = 540;
    public static final int zeroup = 540;

    //1的电平宽度为：600us低电平+17   00us高电平
    public static final int onedown = 540;
    public static final int oneup = 1700;

    //STOP电平宽度为：600us低电平+1600us高电平
    public static final int stopdown = 540;
    public static final int stopup = 40500;

    //repeat电平宽度为：600us低电平+1600us高电平
    public static final int repeatdown = 9000;
    public static final int repeatup = 2200;


    /**
     * 启动
     */
    public static final int[] power_data = {
            startdown, startup,//起始码
//-----------------------------------------------
            onedown, oneup,onedown, oneup,onedown, oneup,onedown, oneup,
            onedown, oneup,zerodown, zeroup,onedown, oneup,onedown, oneup,  //0xFB --- 1111 1011

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,  //0x04 --- 0000 0100

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,
            zerodown, zeroup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0x12 --- 0001 0010

            onedown, oneup,onedown, oneup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,onedown, oneup,zerodown, zeroup,onedown, oneup,  //0xED --- 1110 1101
//STOP
            stopdown,stopup,
            //repeat
            repeatdown,repeatup,
//停止位-----------------------------
            zerodown,
    };

    /**
     * 启动
     */
    public static final int[] power_norepeat_data = {
            startdown, startup,//起始码
//-----------------------------------------------
            onedown, oneup,onedown, oneup,onedown, oneup,onedown, oneup,
            onedown, oneup,zerodown, zeroup,onedown, oneup,onedown, oneup,  //0xFB --- 1111 1011

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,  //0x04 --- 0000 0100

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,
            zerodown, zeroup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0x12 --- 0001 0010

            onedown, oneup,onedown, oneup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,onedown, oneup,zerodown, zeroup,onedown, oneup,  //0xED --- 1110 1101

//停止位-----------------------------
            zerodown,
    };

}

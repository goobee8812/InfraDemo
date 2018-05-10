package com.skylandertech.www.infrademo.utils;

/**  DM907遥控器
 * Created by tangj on 2018/3/27.
 */

public class DmCodeCommand {
    //编码规则
    //起始码S电平宽度为：9000us低电平+450us高电平
    public static final int startdown = 9000;
    public static final int startup = 4500;

    //数据码由0，1组成：
    //0的电平宽度为：600us低电平+600us高电平，
        public static final int zerodown = 580                                                                  ;
    public static final int zeroup = 580;

    //1的电平宽度为：600us低电平+1600us高电平
    public static final int onedown = 580;
    public static final int oneup = 1700;

    //STOP电平宽度为：600us低电平+1600us高电平
    public static final int stopdown = 580;
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
            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0xFA --- 1111 1010

            onedown, oneup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,  //0x80   --- 1000 0000

            zerodown, zeroup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,  //0x28 --- 0010 1000

            onedown, oneup,onedown, oneup,zerodown, zeroup,onedown, oneup,
            zerodown, zeroup,onedown, oneup,onedown, oneup,onedown, oneup,  //0xD7 --- 1101 0111
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
            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0xFA --- 1111 1010

            onedown, oneup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,  //0x80   --- 1000 0000

            zerodown, zeroup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,  //0x28 --- 0010 1000

            onedown, oneup,onedown, oneup,zerodown, zeroup,onedown, oneup,
            zerodown, zeroup,onedown, oneup,onedown, oneup,onedown, oneup,  //0xD7 --- 1101 0111

//停止位-----------------------------
            zerodown,
    };

}

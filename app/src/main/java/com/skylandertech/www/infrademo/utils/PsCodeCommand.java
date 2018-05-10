package com.skylandertech.www.infrademo.utils;

/**  松下遥控器
 * Created by tangj on 2018/3/27.
 */

public class PsCodeCommand {
    //编码规则
    //起始码S电平宽度为：3600us低电平+1700us高电平
    public static final int startdown = 3600;
    public static final int startup = 1700;

    //数据码由0，1组成：
    //0的电平宽度为：450us低电平+450us高电平，
    public static final int zerodown = 450;
    public static final int zeroup = 450;

    //1的电平宽度为：450us低电平+1300us高电平
    public static final int onedown = 450;
    public static final int oneup = 1300;


    public static final int[] baseArr = {
            startdown, startup,//起始码

            zerodown, zeroup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,  //0x40 -- 0100 0000

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,    //0x04 -- 0000 0100

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,    //0x01 -- 0000 0001

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,
            zerodown, zeroup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,    //0x12 --- 0001 0010

            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,
            zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,zerodown, zeroup,  //0x00 --- 0000 0000
            //可修改参数
            onedown, oneup,zerodown, zeroup,onedown, oneup,onedown, oneup,
            onedown, oneup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,  //0xBC --- 1011 1100

            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,onedown, oneup,onedown, oneup,onedown, oneup,  //0xAF --- 1010 1111

            //停止位
            zerodown,
    };
    //电源控制
    public static int[] test_data = {
            onedown, oneup,zerodown, zeroup,onedown, oneup,onedown, oneup,
            onedown, oneup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,  //0xBC --- 1011 1100

            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,onedown, oneup,onedown, oneup,onedown, oneup,  //0xAF --- 1010 1111
    };
    public static int[] up_data = {
            zerodown, zeroup,onedown, oneup,zerodown, zeroup,onedown, oneup,
            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0x5A --- 0101 1010

            zerodown, zeroup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,
            onedown, oneup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,  //0x49 --- 0100 1001
    };
    public static int[] down_data = {
            onedown, oneup,onedown, oneup,zerodown, zeroup,onedown, oneup,
            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0xDA --- 1101 1010

            onedown, oneup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,
            onedown, oneup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,  //0xC9 --- 1100 1001
    };
    public static int[] left_data = {
            zerodown, zeroup,zerodown, zeroup,onedown, oneup,onedown, oneup,
            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0x3A --- 0011 1010

            zerodown, zeroup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,  //0x29 --- 0010 1001
    };
    public static int[] right_data = {
            onedown, oneup,zerodown, zeroup,onedown, oneup,onedown, oneup,
            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,  //0xBA --- 1011 1010

            onedown, oneup,zerodown, zeroup,onedown, oneup,zerodown, zeroup,
            onedown, oneup,zerodown, zeroup,zerodown, zeroup,onedown, oneup,  //0xA9 --- 1010 1001
    };
    public static int[] done_data = {
            zerodown, zeroup,onedown, oneup,zerodown, zeroup,zerodown, zeroup,
            onedown, oneup,onedown, oneup,onedown, oneup,zerodown, zeroup,  //0x4E --- 0100 1110

            zerodown, zeroup,onedown, oneup,zerodown, zeroup,onedown, oneup,
            onedown, oneup,onedown, oneup,zerodown, zeroup,onedown, oneup,  //0x5D --- 0101 1101
    };
}

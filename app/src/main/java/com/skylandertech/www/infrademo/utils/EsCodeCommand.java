package com.skylandertech.www.infrademo.utils;

/**  愛普森遥控器
 * Created by tangj on 2018/3/27.
 */

public class EsCodeCommand {
    //编码规则
    //起始码S电平宽度为：3600us低电平+1700us高电平
    public static final int startdown = 2400;
    public static final int startup = 570;

    //连接码
    public static final int connectdown = 1200;
    public static final int connectup = 570;
    //数据码由0，1组成：
    //0的电平宽度为：600us低电平+600us高电平，
    public static final int zerodown = 570;
    public static final int zeroup = 570;

    //1的电平宽度为：600us低电平+1600us高电平
    public static final int onedown = 570;
    public static final int oneup = 1200;

    /**
     * 启动
     */
    public static final int[] on_data = {
            startdown, startup,//起始码
//-----------------------------------------------
            zerodown, zeroup,
            connectdown,connectup,
            zerodown, zeroup,
            onedown,oneup,
            onedown,oneup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            //停止位
            zerodown,
    };
    /**
     * 待机
     */
    public static final int[] standby_data = {
            startdown, startup,//起始码
//-----------------------------------------------
            zerodown, zeroup,
            connectdown,connectup,
//-----------------------------------------------
            zerodown, zeroup,
            onedown,oneup,
            zerodown, zeroup,
            connectdown,oneup,
            onedown,oneup,
            connectdown,connectup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            //停止位
            zerodown,
    };

    /**
     * 启动
     */
    public static final int[] on_ifr_data = {
            startdown, startup,//起始码
//-----------------------------------------------
            zerodown, zeroup,
            connectdown,connectup,
            zerodown, zeroup,
            onedown,oneup,
            onedown,oneup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            //停止位
            zerodown,75000,

            startdown, startup,//起始码
//-----------------------------------------------
            zerodown, zeroup,
            connectdown,connectup,
            zerodown, zeroup,
            onedown,oneup,
            onedown,oneup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            //停止位
            zerodown,75000,

            startdown, startup,//起始码
//-----------------------------------------------
            zerodown, zeroup,
            connectdown,connectup,
            zerodown, zeroup,
            onedown,oneup,
            onedown,oneup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            zerodown, zeroup,
            //停止位
            zerodown,75000,
    };
}

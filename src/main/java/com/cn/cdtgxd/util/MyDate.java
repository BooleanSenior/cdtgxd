package com.cn.cdtgxd.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MyDate {
    public static SimpleDateFormat toFormatTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df;
    }

    public static String toFormatreduce(String starttime,String endtime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = df.parse(endtime);
        java.util.Date date=df.parse(starttime);
        long l=now.getTime()-date.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
        String backTime = day+"天"+hour+"小时"+min+"分"+s+"秒";
        return backTime;
    }
}

package com.example.zane.bookmanager.utils;

import java.util.Calendar;

/**
 * Created by Zane on 16/3/22.
 */
public class TimeCaluUtils {

    private static Calendar calendar_start = Calendar.getInstance();
    private static Calendar calendar_now = Calendar.getInstance();
//    private static Calendar calendar_day = Calendar.getInstance();
//    private static Calendar calendar_month = Calendar.getInstance();

    //返回现在距离开学第一天隔了多少天
    public static int CaluDays(long start){

        calendar_start.setTimeInMillis(start);
        calendar_now.setTimeInMillis(System.currentTimeMillis());

        return (int)((calendar_now.getTimeInMillis() - calendar_start.getTimeInMillis()) / (60 * 60 * 24 * 1000) + 1);
    }
//
//
//    //返回某天的日期
//    public static int getCurDay(int n){
//        int days = n + 1;
//        calendar_day.setTimeInMillis(calendar_start.getTimeInMillis() + (long)days * 60 * 60 * 24 * 1000);
//        return calendar_day.get(Calendar.DAY_OF_MONTH);
//    }
//
//    //返回当前周
//    public static int getCurWeek(int n){
//
//        int days = n;
//        int weekDay;
//
//        if ((days % 7) == 0){
//            weekDay = days / 7;
//        } else {
//            weekDay = (days / 7 + 1);
//        }
//
//        return weekDay;
//    }
//
//    //返回当前月
//    public static int getCurMonth(int n){
//        int week_day = n;
//        calendar_month.setTimeInMillis(calendar_start.getTimeInMillis() + (long)week_day * 60 * 60 * 24 * 1000);
//        return calendar_month.get(Calendar.MONTH) + 1;
//    }
}

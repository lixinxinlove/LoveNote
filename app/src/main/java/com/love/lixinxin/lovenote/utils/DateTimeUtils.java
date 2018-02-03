package com.love.lixinxin.lovenote.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static final String HH_mm = "HH:mm";
    public static final String HH_mm_ss = "HH:mm:ss";
    public static final String MM_Yue_dd_Ri = "MM月dd日";
    public static final String MM_yy = "MM/yy";
    public static final String M_Yue_d_Ri = "M月d日";
    public static final long ONE_DAY = 86400000L;
    public static final long ONE_HOUR = 3600000L;
    public static final long ONE_MINUTE = 60000L;
    public static final long ONE_SECOND = 1000L;
    private static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM-dd", "yyyyMMdd"};
    public static final String dd_MM = "dd/MM";
    public static boolean hasServerTime = false;
    public static long tslgapm = 0L;
    public static String tss;
    private static String[] weekdays = {"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static String[] weekdays1 = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";
    public static final String yyyy_MM = "yyyy-MM";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyy_Nian_MM_Yue_dd_Ri = "yyyy年MM月dd日";



    public static Calendar getCurrentDateTime() {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setLenient(false);
        if (hasServerTime)
            localCalendar.setTimeInMillis(localCalendar.getTimeInMillis() + tslgapm);
        return localCalendar;
    }


    /**
     * 时间戳
     *
     * @return
     */
    public static long getTimeStamp() {
        long time = System.currentTimeMillis();
        return time;
    }

    public static String getTodayString() {
        long time = getTimeStamp();
        return DateTimeUtils.timeForDate(time, yyyy_MM_dd);
    }

    /**
     * 获得指定时间的时间戳
     *
     * @param dateString
     * @param format     yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static long getTime(String dateString, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Date date;
        try {
            date = df.parse(dateString);
            long time = date.getTime() / 1000;
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 两个时间之间的天数 ps 毫秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(long date1, long date2) {
        if (date1 == 0)
            return 0;
        if (date2 == 0)
            return 0;
        // 转换为标准时间

        long day = (date2 - date1) / (24 * 60 * 60 * 1000);
        return day;
    }

    // 获取当天时间
    public static String getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
        String hehe = dateFormat.format(now);
        return hehe;
    }



    /**
     * 精确到秒
     *
     * @param nowTime
     * @param day
     * @return
     */
    public static long calculationDateTemp(long nowTime, int day) {
        return nowTime + day * ONE_DAY / 1000;
    }

    public static Calendar getToadyCalendar() {
        Calendar calendar = Calendar.getInstance();
        Date startDate = new Date(getTimeStamp());
        calendar.setTime(startDate);
        return calendar;
    }

    /**
     * 根据时间戳格式化日期
     *
     * @param time
     * @return
     */
    public static String timeForDate(long time, String format) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获得当天整点的时间戳
     *
     * @return
     */
    public static long getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() / 1000) - 60 * 60 * 24;
    }

    /*
     * 秒转化
     */
    public static String formatTime(long ms) {
        String result = "";
        int mi = 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi);
        if (day > 0) {
            result = day + "天" + hour + "小时" + minute + "分" + second + "秒";
        } else if (hour > 0) {
            result = hour + "小时" + minute + "分" + second + "秒";
        } else if (minute > 0) {
            result = minute + "分" + second + "秒";
        } else if (second > 0) {
            result = second + "秒";
        }
        return result;
    }

    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(long date) {
        String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static String getMonthAndDays(long date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(date));
        int dom = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        return month + "月" + dom;
    }


}
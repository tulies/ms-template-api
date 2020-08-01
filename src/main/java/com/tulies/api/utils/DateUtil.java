package com.tulies.api.utils;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;


public class DateUtil {

    public static final String FORMAT_STANDARD = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_MONTH = "MM";
    public static final String FORMAT_DAY = "dd";
    public static final String FORMAT_HOUR = "HH";
    public static final String FORMAT_MINUTE = "mm";
    public static final String FORMAT_SECOND = "ss";

    public static final int FIELD_YEAR = Calendar.YEAR;                  //年份
    public static final int FIELD_MONTH = Calendar.MONTH;                //月份，出来的数据需要+1
    public static final int FIELD_WEEK_OF_YEAR = Calendar.WEEK_OF_YEAR;  //这周在一年中是第几周
    public static final int FIELD_WEEK_OF_MONTH = Calendar.WEEK_OF_MONTH;//这周在该月是第几周
    public static final int FIELD_DAY = Calendar.DAY_OF_MONTH;           //该日在该月中是第几日
    public static final int FIELD_DAY_OF_YEAR = Calendar.DAY_OF_YEAR;    //该日在该年中是第几日
    public static final int FIELD_DAY_OF_WEEK = Calendar.DAY_OF_WEEK;    //该日在该周中是第几日
    public static final int FIELD_HOUR = Calendar.HOUR_OF_DAY;           //小时（24小时制）
    public static final int FIELD_MINUTE = Calendar.MINUTE;              //分钟
    public static final int FIELD_SECOND = Calendar.SECOND;              //秒
    public static final int FIELD_MILLISECOND = Calendar.MILLISECOND;    //毫秒

    /**
     * 获取当前时间
     *
     * @return Date 类型的当前时间
     */
    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取当前时间的指定时间点
     *
     * @param field Calendar中的类型
     * @return 输出
     */
    public static int getCurrentDateField(int field) {
        Calendar calendar = Calendar.getInstance();
        int result = calendar.get(field);
        return result;
    }

    /**
     * 将当前时间转换为字符串形式
     *
     * @return 默认格式的输出:yyyy-MM-dd HH:mm:ss
     */
    public static String getStrFromDate() {
        return getStrFromDate(null, null);
    }

    /**
     * 将当前时间转换为字符串形式，可以指定输出格式
     *
     * @param format 输出格式
     * @return 指定的输出格式的时间字符串
     */
    public static String getStrFromDate(String format) {
        return getStrFromDate(null, format);
    }

    /**
     * 将指定时间转换为字符串格式，指定输出格式
     *
     * @param date 指定时间
     * @return Stirng
     */
    public static String getStrFromDate(Date date) {
        return getStrFromDate(date, null);
    }

    /**
     * 将指定格式转换为字符串格式，指定输出格式
     *
     * @param date   指定格式
     * @param format 指定格式
     * @return String
     */
    public static String getStrFromDate(Date date, String format) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        if (StringUtils.isBlank(format)) {
            format = FORMAT_STANDARD;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(calendar.getTime());
    }

    /**
     * 字符串时间变更为Date,使用默认格式
     *
     * @param dateStr 字符串时间
     * @return Date类型时间
     * @throws ParseException 转换错误
     */
    public static Date getDateFromStr(String dateStr) throws ParseException {
        return getDateFromStr(dateStr, FORMAT_STANDARD);
    }

    /**
     * 字符串变更时间为Date，可指定转换格式
     *
     * @param dateStr 字符串时间
     * @param format  转换格式
     * @return Date类型时间
     * @throws ParseException 转换错误
     */
    public static Date getDateFromStr(String dateStr, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            format = FORMAT_STANDARD;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(dateStr);
    }

    /**
     * 以当前时间推算一定时间间隔的时间点
     *
     * @param fieldMap key=field,value=interval
     * @return Date
     */
    public static Date getDateFromInterval(Map<Integer, Integer> fieldMap) {
        return getDateFromInterval(null, fieldMap);
    }

    /**
     * 以当前时间推算一定时间间隔的时间点，默认的字符串时间格式
     *
     * @param fieldMap key=field,value=interval
     * @return String
     */
    public static String getStrFromInterval(Map<Integer, Integer> fieldMap) {
        return getStrFromInterval(null, fieldMap, null);
    }

    /**
     * 以当前时间推算一定时间间隔的时间点，指定的字符串时间格式
     *
     * @param fieldMap key=field,value=interval
     * @param format   指定的字符串时间格式
     * @return String
     */
    public static String getStrFromInterval(Map<Integer, Integer> fieldMap, String format) {
        return getStrFromInterval(null, fieldMap, format);
    }

    /**
     * 以指定时间推算一定时间间隔的时间点
     *
     * @param date     指定的时间
     * @param fieldMap key=field,value=interval
     * @return Date
     */
    public static Date getDateFromInterval(Date date, Map<Integer, Integer> fieldMap) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        Set<Integer> fields = fieldMap.keySet();
        for (Integer tempField : fields) {
            if (tempField == null) {
                continue;
            }
            if (fieldMap.get(tempField) == null) {
                continue;
            }
            calendar.add(tempField, fieldMap.get(tempField));
        }
        return calendar.getTime();
    }

    /**
     * 以指定时间推算一定时间间隔的时间点，默认的字符串时间格式
     *
     * @param date     指定的时间点
     * @param fieldMap key=field,value=interval
     * @return String
     */
    public static String getStrFromInterval(Date date, Map<Integer, Integer> fieldMap) {
        return getStrFromInterval(date, fieldMap, null);
    }

    /**
     * 以指定的时间推算一定时间间隔的时间点，指定的字符串时间格式
     *
     * @param date     指定的时间点
     * @param fieldMap key=field,value=interval
     * @param format   指定的字符串时间格式
     * @return String
     */
    public static String getStrFromInterval(Date date, Map<Integer, Integer> fieldMap, String format) {
        if (StringUtils.isBlank(format)) {
            format = FORMAT_STANDARD;
        }
        Date dateResult = getDateFromInterval(date, fieldMap);
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(dateResult);
    }


    /**
     * 以当前时间与目标字符串的时间比较
     *
     * @param targetStr 目标时间点
     * @return 在target之前返回-1，相等返回0，在target之后返回1
     * @throws ParseException 格式化错误
     */
    public static int compareTo(String targetStr) throws ParseException {
        return compareTo(Calendar.getInstance().getTime(), targetStr);
    }

    /**
     * 以指定时间与目标字符串时间比较
     *
     * @param date      指定时间
     * @param targetStr 目标字符串时间
     * @return 在target之前返回-1，相等返回0，在target之后返回1
     * @throws ParseException 格式化错误
     */
    public static int compareTo(Date date, String targetStr) throws ParseException {
        Date target = getDateFromStr(targetStr);
        return date.compareTo(target);
    }

    /**
     * 以指定时间字符串与目标字符串的时间比较
     *
     * @param dateStr   指定时间点
     * @param targetStr 目标时间点
     * @return 在target之前返回-1，相等返回0，在target之后返回1
     * @throws ParseException 格式化错误
     */
    public static int compareTo(String dateStr, String targetStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_STANDARD);
        Date date;
        if (StringUtils.isBlank(dateStr)) {
            date = Calendar.getInstance().getTime();
        } else {
            date = formatter.parse(dateStr);
        }
        Date target = formatter.parse(targetStr);
        return date.compareTo(target);
    }

    /**
     * 两个日期相差多少天
     * @param startDate
     * @param endDate
     * @return
     */
    public static long days(String startDate,String endDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long to = 0,from = 0;
        try {
            to = df.parse(endDate).getTime();
            from = df.parse(startDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int num = (int) ((to - from) / (1000 * 60 * 60 * 24));
        return num;
    }


}

package com.devwu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by WuNan on 16/12/26.
 * Date工具类，用于DateStr、Date、时间戳之间的转换
 */

public class DateUtil {
    public SimpleDateFormat mDateFormat;

    public enum FORMAT_TYPE_ENUM {
        ALL("yyyy-MM-dd HH:mm:ss"),
        Y_M_D_H_M("yyyy-MM-dd HH:mm"),
        Y_M_D("yyyy-MM-dd");
        private String formatStr;

        FORMAT_TYPE_ENUM(String formatStr) {
            this.formatStr = formatStr;
        }

        @Override
        public String toString() {
            return this.formatStr;
        }
    }

    /**
     * 默认构造器，采用ALL模式
     */
    public static DateUtil create() {
        return create(FORMAT_TYPE_ENUM.ALL);
    }

    /**
     * 自定义构造器，可使用任意模式
     *
     * @param type
     */
    public static DateUtil create(FORMAT_TYPE_ENUM type) {
        return new DateUtil(type.toString());
    }

    /**
     * 隐藏构造方法
     */
    private DateUtil(String formatStr) {
        this.mDateFormat = new SimpleDateFormat(formatStr, Locale.CHINA);
    }


    /**
     * 将Date转化为字符串
     * @param date
     * @return
     */
    public String stringOfDate(Date date) {

        return this.mDateFormat.format(date);
    }

    /**
     * 从字符串转化为Date
     * @param dateStr
     * @return
     */
    public Date dateOfString(String dateStr) {
        Date date = null;
        try {
            date = this.mDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将dateStr转化为时间戳
     * @param dateStr
     * @return
     */
    public long timestampOfDateStr(String dateStr) {
        return dateOfString(dateStr).getTime() / 1000;
    }

    /** 将时间戳转化为Date
     * @param timestamp
     * @return
     */
    public Date dateOfTimestamp(long timestamp) {
        return new Date(timestamp * 1000);
    }

    /** 将时间戳转化为DateStr
     * @param timestamp
     * @return
     */
    public String dateStringOfTimestamp(long timestamp) {
        return stringOfDate(dateOfTimestamp(timestamp));
    }

    
}

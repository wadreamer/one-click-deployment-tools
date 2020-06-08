package com.cfg.deploytools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * ClassName: DateUtils
 * Description:
 * date: 2020/6/8 9:31
 *
 * @author CFG
 * @since JDK 1.8
 */
public class DateUtils {

    /*
     * @Author wadreamer
     * @Description //TODO 将String字符串转换为java.util.Date格式日期
     * @Date 9:32 2020/6/8
     * @Param [strDate, dateFormat]
     * 表示日期的字符串
     * 传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
     * @return java.util.Date
     **/
    public static java.util.Date strToUtilDate(String strDate, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        java.util.Date date = null;
        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将String字符串转换为java.sql.Timestamp格式日期,用于数据库保存
     * @Date 9:33 2020/6/8
     * @Param [strDate, dateFormat]
     * 表示日期的字符串
     * 传入字符串的日期表示格式（如："yyyy-MM-dd HH:mm:ss"）
     * @return java.sql.Timestamp
     **/
    public static java.sql.Timestamp strToSqlDate(String strDate, String dateFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        java.util.Date date = null;
        try {
            date = sf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTime());
        return dateSQL;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将java.util.Date对象转化为String字符串
     * @Date 9:33 2020/6/8
     * @Param [date, strFormat]
     * 要格式的java.util.Date对象
     * 输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
     * @return java.lang.String
     **/
    public static String dateToStr(java.util.Date date, String strFormat) {
        SimpleDateFormat sf = new SimpleDateFormat(strFormat);
        String str = sf.format(date);
        return str;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将java.sql.Timestamp对象转化为String字符串
     * @Date 9:34 2020/6/8
     * @Param [time, strFormat]
     * 要格式的java.sql.Timestamp对象
     * 输出的String字符串格式的限定（如："yyyy-MM-dd HH:mm:ss"）
     * @return java.lang.String
     **/
    public static String dateToStr(java.sql.Timestamp time, String strFormat) {
        DateFormat df = new SimpleDateFormat(strFormat);
        String str = df.format(time);
        return str;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将java.sql.Timestamp对象转化为java.util.Date对象
     * @Date 9:34 2020/6/8
     * @Param [time]
     * 要转化的java.sql.Timestamp对象
     * @return java.util.Date
     **/
    public static java.util.Date timeToDate(java.sql.Timestamp time) {
        return time;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 将java.util.Date对象转化为java.sql.Timestamp对象
     * @Date 9:34 2020/6/8
     * @Param [date]
     * 要转化的java.util.Date对象
     * @return java.sql.Timestamp
     **/
    public static java.sql.Timestamp dateToTime(java.util.Date date) {
        String strDate = dateToStr(date, "yyyy-MM-dd HH:mm:ss SSS");
        return strToSqlDate(strDate, "yyyy-MM-dd HH:mm:ss SSS");
    }

    /*
     * @Author wadreamer
     * @Description //TODO 返回表示系统当前时间的java.util.Date对象
     * @Date 9:35 2020/6/8
     * @Param []
     * @return java.util.Date
     **/
    public static java.util.Date nowDate(){
        return new java.util.Date();
    }

    /*
     * @Author wadreamer
     * @Description //TODO 返回表示系统当前时间的java.sql.Timestamp对象
     * @Date 9:35 2020/6/8
     * @Param []
     * @return java.sql.Timestamp
     **/
    public static java.sql.Timestamp nowTime(){
        return dateToTime(new java.util.Date());
    }


}

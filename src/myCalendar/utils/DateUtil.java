package myCalendar.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
    //  年月日
    public final static String DATE_FORMAT       = "yyyy-MM-dd";
    public final static String DATE_FORMAT_FORMAT       = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_MIN      = "yyyy-MM-dd HH:mm";
    public final static String DATE_FORMAT1        = "yyyyMMdd";


    public static String format(Date date, String fmt) {
        try {
            if(date==null) {
                date=new Date();
            }
            if(fmt==null) {
                fmt=DATE_FORMAT;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * @Description: 转化时间
     * @Return:
     * @Param:
     * @Author: caiyl@hsyuntai.com
     * @Date: 2016/11/13 14:54
     */
    public static Date parseDate(String dateStr, String formatStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatStr==null||formatStr.trim().length()==0?DATE_FORMAT_FORMAT:formatStr);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }


}

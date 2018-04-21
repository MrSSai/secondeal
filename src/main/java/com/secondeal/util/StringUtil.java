package com.secondeal.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 将Object 转化成 String类型
     *
     * @param object
     * @return
     */
    public static String parseString(Object object) {
        return (object == null) ? "null" : object.toString();
    }

    /**
     * 将Object 转化成 Double类型
     *
     * @param object
     * @return
     */
    public static Double parseDouble(Object object) {
        Double valueBef = new Double(object.toString());
        return valueBef;
    }

    /**
     * 将Object 转化成 Integer类型
     *
     * @param object
     * @return
     */
    public static Integer parseInteger(Object object) {
        return Integer.parseInt(String.valueOf(object));
    }

    /**
     * 获得一个uuid
     *
     * @return uuid
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 判断是否为手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowDate() {
//        Calendar c=Calendar.getInstance();
//        int y=c.get(Calendar.YEAR);
//        int m=c.get(Calendar.MONTH);
//        int d=c.get(Calendar.DATE);
//        String date=y+"-"+m+"-"+d;
//        Date date1=java.sql.Date.valueOf(date);
//        return date1;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    /**
     * 获取当前时间
     *
     * @return datetime
     */
    public static Date getDate() {

        Date day = new Date();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = df.format(day);
        Date date = null;
        try {
            date = df.parse(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


}

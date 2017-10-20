package com.happy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/20/10:35
 * @Desc:
 **/
public class Dates {



    public static boolean close(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);

        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String begin = today + " 06:00:00";
        String end = today + " 20:00:00";
        Date b = sdf.parse(begin);
        Date e = sdf.parse(end);

        if(date.after(b) && date.before(e)){
            return false;//未打烊
        }else {
            return true;//打烊
        }

    }



    public static boolean close(String begin, String end, boolean dayflag) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date cur = new Date();

        String today = sdf.format(cur) + " ";
        String[] begins = begin.split(":");
        String[] ends = end.split(":");
        begin = today + begin;
        end = today + end;

        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date b = sdf.parse(begin);
        Date e = sdf.parse(end);

        if (dayflag){

            if(Integer.valueOf(begins[0]) <= Integer.valueOf(ends[0])){
                throw new  ParseException("次日时，结束时间应该小于开始时间",62);
            }

            if(cur.before(b) && cur.after(e)){//处于营业时间段
                return false;
            }else {
                return true;
            }
        }else {

            if(Integer.valueOf(begins[0]) >= Integer.valueOf(ends[0])){
                throw new  ParseException("非次日时，结束时间应该大于开始时间",73);
            }

            if(!cur.before(b) && !cur.after(e)){//处于打烊时间段
                return true;
            }else {
                return false;
            }
        }
    }



    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2017-10-20 12:41:42");


//        System.out.println(Dates.close(date));


        System.out.println(Dates.close("20:00:00", "06:00:00", true));
        System.out.println(Dates.close("06:00:00", "20:00:00", false));
    }

}

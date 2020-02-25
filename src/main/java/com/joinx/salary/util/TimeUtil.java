package com.joinx.salary.util;

import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeUtil {
   public static Calendar calendar = null;
   /**
    * 功能描述：返回毫秒
    *
    * @param date 日期
    * @return 返回毫秒
    */
   public static long getMillis(Date date) {
      calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.getTimeInMillis();
   }
   /**
    * 功能描述：日期相减
    *
    * @param date  Date 日期
    * @param date1 Date 日期
    * @return 返回相减后的日期
    */
   public static int calculateDay(Date date, Date date1) {
      return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
   }
   
   /**
    * 功能描述：计算一天中的小时差值
    * @param start 开始时间
    * @param end 结束时间
    * @return
    */
   public static int calculateHour(Date start, Date end){
      if( checkHour(start)){
         int hourcount=((int) ((getMillis(end) - getMillis(start))) / (3600 * 1000));
         hourcount-=12;
         return hourcount;
      }
      return (int)((getMillis(end) - getMillis(start)) / (3600 * 1000));
   }
   public static String getYMDHMS(Date date){
      SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      String format = smf.format(date);
      return format;
   }

   /**
    * 比较两个日期的大小
    * @param param1
    * @param param2
    * @return
    */
   public static boolean compare(Date param1,Date param2){
      return param1.getTime()- param2.getTime()>0?true:false;
   }

   public static  java.util.Date getCurrent(){
      return new Date();
   }
   public static boolean checkHour(Date date){
      SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      String format = smf.format(date);
      return format.contains("12:00:00");
   }
   public static String getYMD(Date date){
      SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd");
      String format = smf.format(date);
      return format;
   }
   public static Date getParse(String parse){
      SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd");
      Date date=null;
      try {
         date= smf.parse(parse);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return date;
   }
   // 计算日期的差日
   private static int diff(Date start, Date end) {
      Interval interval = new Interval(start.getTime(), end.getTime());
      Period period = interval.toPeriod();
      int days=period.getDays();
//      int days=period.getMonths()* ;
      if(period.getHours()>4){
         return days+1;
      }
      System.out.printf(
              "%d years, %d months, %d days, %d hours, %d minutes, %d seconds%n",
              period.getYears(), period.getMonths(), period.getDays(),
              period.getHours(), period.getMinutes(), period.getSeconds());
      return days;
   }
   public static boolean contains(){
      String ymd = getYMD(new Date());
      Date parse = TimeUtil.getParse(ymd);
      int day = parse.getDay();
      if (day==5)
      {
         return true;
      }else
      return false;
   }

   /**
    * 获取当前月份前一月所在月份开始的时间
    * @return
    * @throws ParseException
    */
   public static Date getLastMonthBegin(){
      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      //设置月份
      c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
      //设置为1号,当前日期既为本月第一天
      c.set(Calendar.DAY_OF_MONTH, 1);
      //将小时至0
      c.set(Calendar.HOUR_OF_DAY, 0);
      //将分钟至0
      c.set(Calendar.MINUTE, 0);
      //将秒至0
      c.set(Calendar.SECOND,0);
      //将毫秒至0
      c.set(Calendar.MILLISECOND, 0);
      // 获取本月第一天的时间戳
      return new Date(c.getTimeInMillis());
   }

   /**
    * 获取当前月份前一月所在月份结束的时间
    * @param
    * @return
    */
   public static Date getLastMonthEnd() {
      Calendar c = Calendar.getInstance();
      c.setTime(new Date());
      c.set(Calendar.MONTH,c.get(Calendar.MONTH)-1);
      //设置为当月最后一天
      c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
      //将小时至23
      c.set(Calendar.HOUR_OF_DAY, 23);
      //将分钟至59
      c.set(Calendar.MINUTE, 59);
      //将秒至59
      c.set(Calendar.SECOND,59);
      //将毫秒至999
      c.set(Calendar.MILLISECOND, 999);
      // 获取本月最后一天的时间戳
      return new Date(c.getTimeInMillis());
   }

   /**
    *获取输入的月份的所有双休日
    * @param start 起始日期
    * @return
    */
   public static int getMonthWeekDays(Date start){
      Calendar instance = Calendar.getInstance();
      instance.setTime(start);
      int month = instance.get(Calendar.MONTH);//第几个月
      int year = instance.get(Calendar.YEAR);//年份数值
      Calendar calendar = new GregorianCalendar(year, month, 1);
      int i = 1;
      int count=0;
      while (calendar.get(Calendar.MONTH) < month + 1) {
         calendar.set(Calendar.WEEK_OF_YEAR, i++);

         calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
         if (calendar.get(Calendar.MONTH) == month) {
            System.out.printf("星期天：%tF%n", calendar);
            count++;
         }
         calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
         if (calendar.get(Calendar.MONTH) == month) {
            System.out.printf("星期六：%tF%n", calendar);
            count++;
         }
      }
      return count;
   }
   //获取一个月天数
   public static int getDaysOfMonth(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
   }
   public static void main(String[] args) throws ParseException {
      TimeUtil.getLastMonthBegin();
      TimeUtil.getLastMonthEnd();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
         Date date1 = simpleDateFormat.parse("2020-03-01 11:30:10");
         Date date2 = simpleDateFormat.parse("2020-03-03 11:35:55");
         TimeUtil.diff(date1,date2);
      int i = TimeUtil.calculateHour(date1, date2);
      System.out.println(i+"???????");
      boolean compare = TimeUtil.compare(date2, date1);
      System.out.println(compare+"-----");

   }
}

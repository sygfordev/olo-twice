package com.hdfs.olo.olo_web.plugins.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.regex.Pattern;
/**
 * 
 * @author: 李逸聪
 * @date: May 22, 2011 
 * @description:
 * 	 时间帮助类
 */
public class DateTimeHelper {
	/**
	 * 一天所对应的秒数
	 */
	private static int ONEDATE = 24 * 60 * 60 * 1000;
	/**
	 * 缺省的日期显示格式： yyyy-MM-dd
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 缺省的日期时间显示格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyyMMdd字符串
	 */
	public static final String DEFAULT_DATE_STRING = "yyyyMMdd";
	/**
	 * yyyyMMddHHmmss字符串
	 */
	public static final String DEFAULT_DATETIME_STRING = "yyyyMMddHHmmss";

	/**
	 * 将字符串转成时间
	 * @param str
	 * @return
	 */
	public static Date createDate(String str) {
		return strToDate(str,DEFAULT_DATE_FORMAT);
	}

	/**
	 * 格式化输出日期
	 * @param date
	 * 			日期
	 * @param format
	 * 			格式
	 * @return
	 */
	public static String format(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date strToDate(String str,String formatStr) {
	   SimpleDateFormat format = new SimpleDateFormat(formatStr);
	   Date date = null;
	   try {
		   date = format.parse(str);
	   } catch (ParseException e) {
		   e.printStackTrace();
	   }
	   return date;
	}


	/**
	 * 得到yyyyMMddHHmmss的当前时间字符串
	 * @return
	 */
	public static String getDateID() {
		return format(new Date(), DEFAULT_DATETIME_STRING);
	}

	/**
	 * 得到yyyy-MM-dd HH:mm:ss格式的时间字符串
	 * @param date 
	 * 			时间
	 * @return
	 */
	public static String getDateID(Date date) {
		return format(date, DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 得到yyyyMMdd的当前时间字符串
	 * @return
	 */
	public static String getDate8ID() {
		return format(new Date(), DEFAULT_DATE_STRING);
	}

	/**
	 * 得到yyyyMMddHHmmssSSS的当前时间字符串
	 * @return
	 */
	public static String getDate17ID() {
		return format(new Date(), "yyyyMMddHHmmssSSS");
	}

	/**
	 * 得到yyyy-MM-dd HH:mm:ss格式的当前时间字符串
	 * @return
	 */
	public static String getNowDateID() {
		return format(new Date(), DEFAULT_DATETIME_FORMAT);
	}
	
	/**
	 * 将时间字符串转成Timestamp
	 * @param dateString
	 * @return
	 */
	public static Timestamp getTimestampByDateString(String dateString)
	{
		
		Date date = createDate(dateString);
		return Timestamp.valueOf(getDateID(date));
	}
	
	/**
	 * 通过参数获取时间相应信息
	 * @param date
	 * 		日期
	 * @param field
	 * 		参数
	 * @return
	 */
	public static int getGeneralByParameter(Date date, int field) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(field);
	}

	/**
	 * 得到年份
	 * @param date
	 * 		日期
	 * @return
	 */
	public static int getYear(Date date) {
		return getGeneralByParameter(date, Calendar.YEAR);
	}

	/**
	 * 得到月份
	 * @param date
	 * 		日期
	 * @return
	 */
	public static int getMonth(Date date) {
		return getGeneralByParameter(date, Calendar.MONTH) + 1;
	}

	/**
	 * 得到日份
	 * @param date
	 * 		日期
	 * @return
	 */
	public static int getDay(Date date) {
		return getGeneralByParameter(date, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到小时
	 * @param date
	 * 		日期
	 * @return
	 */
	public static int getHour(Date date) {
		return getGeneralByParameter(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到分钟
	 * @param date
	 * 		日期
	 * @return
	 */
	public static int getMinute(Date date) {
		return getGeneralByParameter(date, Calendar.MINUTE);
	}

	/**
	 * 得到秒钟
	 * @param date
	 * 		日期
	 * @return
	 */
	public static int getSecond(Date date) {
		return getGeneralByParameter(date, Calendar.SECOND);
	}

	/**
	 * 得到毫秒
	 * @param date
	 * 			日期
	 * @return
	 */
	public static long getMilliseconds(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 将指定的天数加到时间
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDate(Date date, int day) {
		return addGeneralByParameter(date, Calendar.DATE, day);
	}

	/**
	 * 将指定的小时数加到时间
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHours(Date date, int hours) {
		return addGeneralByParameter(date, Calendar.HOUR_OF_DAY, hours);
	}

	/**
	 * 将指定的分钟数加到时间
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date, int minute) {
		return addGeneralByParameter(date, Calendar.MINUTE, minute);
	}

	/**
	 * 将指定的秒数加到时间
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSeconds(Date date, int seconds) {
		return addGeneralByParameter(date, Calendar.SECOND, seconds);
	}

	/**
	 * 通过参数往日期加数量
	 * @param date
	 * @param field
	 * 			参数
	 * @param num
	 * 			数量
	 * @return
	 */
	public static Date addGeneralByParameter(Date date, int field, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, num);
		return calendar.getTime();
	}

	/**
	 * 将指定的毫秒数加到时间
	 * @param date
	 * @param milliseconds
	 * @return
	 */
	public static Date addMilliseconds(Date date, int milliseconds) {
		return addGeneralByParameter(date, Calendar.MILLISECOND, milliseconds);
	}

	/**
	 * 计算两个日期的差值(前提：date2>date1)
	 * @param date1
	 * @param date2
	 * @return 返回天数
	 */
	public static int getBalance(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		return (int) ((time2 - time1) / ONEDATE);
	}

	/** 
	 * 取得指定日期距当时时间的距离 
	 * @param date 
	 * @return 
	 */
	public static String date2String(Date date) {
		String result = "";
		if (null != date) {
			Date now = new Date();
			long period = now.getTime() - date.getTime();
			long minutesPeriod = period / (1000 * 60);
			if (minutesPeriod <= 5) {
				result = "刚刚";
			} else if (minutesPeriod > 5 && minutesPeriod <= 60) {
				result = minutesPeriod + " 分钟前";
			} else if (minutesPeriod > 60 && minutesPeriod <= 24 * 60) {
				result = minutesPeriod / 60 + " 小时前";
			} else if (minutesPeriod > 24 * 60 && minutesPeriod <= 24 * 60 * 7) {
				result = minutesPeriod / (24 * 60) + " 天前";
			} else if (minutesPeriod > 24 * 60 * 7
					&& minutesPeriod <= 24 * 60 * 7 * 4) {
				result = minutesPeriod / (24 * 60 * 7) + " 周前";
			} else if (minutesPeriod > 24 * 60 * 7 * 4
					&& minutesPeriod <= 24 * 60 * 365) {
				result = minutesPeriod / (24 * 60 * 30) + " 个月前";
			} else if (minutesPeriod >= 24 * 60 * 365) {
				result = minutesPeriod / (24 * 60 * 365) + " 年前";
			} else {
				result = getDateID(date);
			}
		} else {
			result = "";
		}
		return result;
	}

	/**
	 * 获得所在日期月份的天数
	 * @param date
	 * @return
	 */
	public static int getCountDayInTheMonth(Date date) {
		//获取月份
		int month = getMonth(date);
		if (month == 12)//如果是12月份
		{
			return 31;
		}
		//得到参数所对应的下个月的日期
		Date nextDate = createDate(getYear(date) + "-" + (month + 1) + "-" + 1);
		//获取参数的天数
		String ddStr = format(
				addGeneralByParameter(nextDate, Calendar.DATE, -1), "dd");
		return Integer.parseInt(ddStr);

	}

	/**
	 * 计算两个日期相差月份数
	 * 如果前一个日期小于后一个日期，则返回负数
	 * @param one 第一个日期数，作为基准
	 * @param two 第二个日期数，作为比较
	 * @return 两个日期相差月份数
	 */
	public static int diffMonths(Date one, Date two) {

		Calendar calendar = Calendar.getInstance();

		//得到第一个日期的年分和月份数
		calendar.setTime(one);
		int yearOne = calendar.get(Calendar.YEAR);
		int monthOne = calendar.get(Calendar.MONDAY);

		//得到第二个日期的年份和月份
		calendar.setTime(two);
		int yearTwo = calendar.get(Calendar.YEAR);
		int monthTwo = calendar.get(Calendar.MONDAY);

		return Math.abs((yearOne - yearTwo) * 12 + (monthOne - monthTwo));
	}

	/**
	 * 判断是否润年
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isLeapYear(String date) {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = createDate(date);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}
	/**
	 * 比对当前时间
	 * @param str
	 * @param format
	 * @return
	 * 		0：相等
	 *      大于0：当前大于str
	 *      小于0：当前小于str
	 * @throws ParseException
	 */
	public static int compareTime(String str,String format) throws ParseException 
	{
		String str1 = format(new Date(), format);
		return compareTime(str1, str, format);
	}
	/**
	 * 比对时间
	 * @param str1
	 * @param str2
	 * @param format
	 * @return
	 * 		0：相等
	 *      大于0：str1大于str2
	 *      小于0：str1小于str2
	 * @throws ParseException
	 */
	public static int compareTime(String str1,String str2,String format) throws ParseException
	{
		java.text.DateFormat df=new java.text.SimpleDateFormat(format);
        java.util.Calendar c1=java.util.Calendar.getInstance();
        java.util.Calendar c2=java.util.Calendar.getInstance();
        
        c1.setTime(df.parse(str1));
        c2.setTime(df.parse(str2));
       
        int result=c1.compareTo(c2);
        if(result==0)
        	// System.out.println("c1相等c2");
        	return 0;
           
        else if(result<0)
        	// System.out.println("c1小于c2");
        	return -1;
        else
        	// System.out.println("c1大于c2"); 
        	return 1;
	}
	
	/**
     * 常规自动日期格式识别
     * @param str 时间字符串
     * @return Date
     * @author dc
     */
    public static String formatDate(String dateStr) {
	    HashMap<String, String> dateRegFormat = new HashMap<String, String>();
	    dateRegFormat.put(
	        "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
	        "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
	    dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
	        "yyyy-MM-dd-HH-mm");//2014-03-12 12:05
	    dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
	        "yyyy-MM-dd-HH");//2014-03-12 12
	    dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");//2014-03-12
	    dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");//2014-03
	    dateRegFormat.put("^\\d{4}$", "yyyy");//2014
	    dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
	    dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
	    dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");//2014031212
	    dateRegFormat.put("^\\d{8}$", "yyyyMMdd");//20140312
	    dateRegFormat.put("^\\d{6}$", "yyyyMM");//201403
	    dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$",
	        "yyyy-MM-dd-HH-mm-ss");//13:05:34 拼接当前日期
	    dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//13:05 拼接当前日期
	    dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//14.10.18(年.月.日)
	    dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
	    dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)
	  
	    String curDate =new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	    DateFormat formatter1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    DateFormat formatter2;
	    String dateReplace;
	    String strSuccess="";
	    try {
	    	for (String key : dateRegFormat.keySet()) {
	    		if (Pattern.compile(key).matcher(dateStr).matches()) {
	    			formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
	    			if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$")
	    					|| key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {//13:05:34 或 13:05 拼接当前日期
	    				dateStr = curDate + "-" + dateStr;
	    			} else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//21.1 (日.月) 拼接当前年份
	    				dateStr = curDate.substring(0, 4) + "-" + dateStr;
	    			}
	    			dateReplace = dateStr.replaceAll("\\D+", "-");
	    			// System.out.println(dateRegExpArr[i]);
	    			strSuccess = formatter1.format(formatter2.parse(dateReplace));
	    			break;
	    		}
	    	}
	    } catch (Exception e) {
	    	System.err.println("-----------------日期格式无效:"+dateStr);
	    	throw new Exception( "日期格式无效");
	    } finally {
	    	return strSuccess;
	    }
  }
  public static void main(String[] args) {
    String[] dateStrArray=new String[]{
        "2014-03-12 12:05:34",
        "2014-03-12 12:05",
        "2014-03-12 12",
        "2014-03-12",
        "2014-03",
        "2014",
        "20140312120534",
        "2014/03/12 12:05:34",
        "2014/3/12 12:5:34",
        "2014年3月12日 13时5分34秒",
        "201403121205",
        "1234567890",
        "20140312",
        "201403",
        "2000 13 33 13 13 13",
        "30.12.2013",
        "12.21.2013",
        "21.1",
        "13:05:34",
        "12:05",
        "14.1.8",
        "14.10.18"
    };
    for(int i=0;i<dateStrArray.length;i++){
      System.out.println(dateStrArray[i] +"------------------------------".substring(1,30-dateStrArray[i].length())+ formatDate(dateStrArray[i]));
    }
    System.out.println(diffMonths(new Date(),createDate("2021-09-23")));
  }
}
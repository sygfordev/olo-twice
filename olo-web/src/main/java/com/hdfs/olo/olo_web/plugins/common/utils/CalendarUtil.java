package com.hdfs.olo.olo_web.plugins.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author huadf
 *
 */
public class CalendarUtil {

	/**
	 * 计算2个日期之间相差的  相差多少年月日
	 * 比如：2011-02-02 到  2017-03-02 相差 6年，1个月，0天
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public static DayCompare dayComparePrecise(Date fromDate,Date toDate){
	    Calendar  from  =  Calendar.getInstance();
	    from.setTime(fromDate);
	    Calendar  to  =  Calendar.getInstance();
	    to.setTime(toDate);
	    int fromYear = from.get(Calendar.YEAR);
	    int fromMonth = from.get(Calendar.MONTH);
	    int fromDay = from.get(Calendar.DAY_OF_MONTH);
	    int toYear = to.get(Calendar.YEAR);
	    int toMonth = to.get(Calendar.MONTH);
	    int toDay = to.get(Calendar.DAY_OF_MONTH);
	    int year = toYear  -  fromYear;
	    int month = toMonth  - fromMonth;
	    int day = toDay  - fromDay;
	    return new DayCompare.Builder().day(day).month(month).year(year).build();
	}
}

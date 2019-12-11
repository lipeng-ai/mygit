package com.bawei.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/***
 *	根据传入的日期获取年龄
 *	根据传入的参数获取该日期的月初日期，例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-01 00:00:00”
 *	根据传入的参数获取该日器的月末日期，例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-30 23:59:59”，注意月大月小以及闰年。
 *	求未来日期离今天还剩的天数
 *	求过去日期离今天过去的天数
 * @author li'peng
 *
 */
public class DateUtils {
	/***
	 * 根据传入的日期获取年龄
	 * @param date
	 * @return
	 */
	public static long getAge(String date){
		//获取当前时间
		LocalDate now = LocalDate.now();
		LocalDate parse = LocalDate.parse(date);
		long year = ChronoUnit.YEARS.between(parse,now);
		return year;
		
	}
	/***
	 * 根据传入的参数获取该日期的月初日期，例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-01 00:00:00”
	 */
	public static String getDateByMonthInit(String date){
		LocalDate with = LocalDate.parse(date).with(TemporalAdjusters.firstDayOfMonth());
		return with.toString()+" "+LocalTime.MIN.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	/***
	 * 根据传入的参数获取该日器的月末日期，例如给定的日期为“2019-09-19 19:29:39”，返回“2019-09-30 23:59:59”，注意月大月小以及闰年。
	 */
	public static String getDateByMonthLast(String date){
		LocalDate with = LocalDate.parse(date).with(TemporalAdjusters.lastDayOfMonth());
		return with.toString()+" "+LocalTime.MAX.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	/***
	 * 求未来日期离今天还剩的天数
	 */
	public static Long getDaysByFuture(String date){
		LocalDate now = LocalDate.now();
		LocalDate parse = LocalDate.parse(date);
		long days = ChronoUnit.DAYS.between(now,parse);
		return days;
	}
	/***
	 *求过去日期离今天还剩的天数
	 */
	public static Long getDaysByDeparted(String date){
		LocalDate now = LocalDate.now();
		LocalDate parse = LocalDate.parse(date);
		long days = ChronoUnit.DAYS.between(parse,now);
		return days;
	}
}

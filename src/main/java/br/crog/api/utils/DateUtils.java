package br.crog.api.utils;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private static Calendar c = Calendar.getInstance();
	private static int saturdayWeekDay = 7;
	private static int sundayWeekDay = 1;

	public static Date add(Date date, int amount) {
		c.setTime(date);
		c.add(Calendar.DATE, amount);
		return c.getTime();
	}

	public static boolean isWeekDay(Date date) {
		c.setTime(date);
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if (weekDay > sundayWeekDay && weekDay < saturdayWeekDay) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSaturday(Date date) {
		c.setTime(date);
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if (weekDay == saturdayWeekDay) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSunday(Date date) {
		c.setTime(date);
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if (weekDay == sundayWeekDay) {
			return true;
		} else {
			return false;
		}
	}

	// work at 22:00 to 06:00
	public static boolean isNightTime(Date date) {
		boolean isNightTime = false;
		Calendar calendar = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 22);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		if (date.after(c.getTime()) && date.before(calendar.getTime())) {
			isNightTime = true;
		}

		return isNightTime;
	}

	public static Date todayAt00() {
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		return c.getTime();
	}

	public static Date firstDayOfAMonth(int month) {
		c.set(Calendar.YEAR, 2019);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		return c.getTime();
	}

	public static Date lastDayOfAMonth(int month) {
		Date firstDayOfAMonth = firstDayOfAMonth(month);
		c.setTime(firstDayOfAMonth);
		c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return c.getTime();
	}

	public static String getMonthName(int month) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		
		return months[month-1];
	}
}

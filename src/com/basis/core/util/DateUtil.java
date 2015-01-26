package com.basis.core.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.sun.xml.internal.ws.util.UtilException;

/**
 * @author wxliu
 */
public class DateUtil {
	private static Map<String, DateFormat> FormatterPool = new HashMap<String, DateFormat>();
	private static String STANDARD_FORMAT = "yyyy-MM-dd";
	private static String STANDARD_FORMAT_CN = "yyyy年MM月dd日";
	private static String YMDHMS_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static String YMDHMS_FORMAT_CN = "yyyy年MM月dd日 HH时mm分ss秒";

	private static SimpleDateFormat standard_format = new SimpleDateFormat(
			STANDARD_FORMAT);
	private static SimpleDateFormat standard_format_cn = new SimpleDateFormat(
			STANDARD_FORMAT_CN);
	private static SimpleDateFormat ymdhms_format = new SimpleDateFormat(
			YMDHMS_FORMAT);
	private static SimpleDateFormat ymdhms_format_cn = new SimpleDateFormat(
			YMDHMS_FORMAT_CN);

	 Calendar cld;

		public  final long YEAR = 10000000000l;

		public  final long MONTH = 100000000;

		public  final long DAY = 1000000;

		public  final long HOUR = 10000;

		public  final long MINUTE = 100;

		public  long newDate() {
			long date = 0;
			return date;
		}

		public  long setYear(long date, long year) {
			date = year * YEAR + date % YEAR;
			return date;
		}

		public  long setYear(long date, String year) {
			long thisYear = 0;
			try {
				thisYear = Long.parseLong(year);
			} catch (Exception e) {
				return date;
			}

			return setYear(date, thisYear);
		}

		public  long getYear(long date) {

			return date / YEAR;
		}

		public  long setMonth(long date, long month) {
			long year = getYear(date);
			date = date - year * YEAR;
			date = month * MONTH + date % MONTH;

			return year * YEAR + date;
		}

		public  long setMonth(long date, String month) {
			long thisMonth = 0;
			try {
				thisMonth = Long.parseLong(month);
			} catch (Exception e) {
				return date;
			}

			return setMonth(date, thisMonth);
		}

		public  long getMonth(long date) {
			long year = getYear(date);
			date = date - year * YEAR;

			return date / MONTH;
		}

		public  long setDay(long date, long day) {
			long year = getYear(date);
			long month = getMonth(date);
			date = (date - year * YEAR) - month * MONTH;
			date = day * DAY + date % DAY;

			return year * YEAR + month * MONTH + date;
		}

		public  long setDay(long date, String day) {
			long thisDay = 0;
			try {
				thisDay = Long.parseLong(day);
			} catch (Exception e) {
				return date;
			}

			return setDay(date, thisDay);
		}

		public  long getDay(long date) {
			long year = getYear(date);
			long month = getMonth(date);
			date = date - year * YEAR - month * MONTH;

			return date / DAY;
		}

		public  long setHour(long date, long hour) {
			long thisDate = 0;
			long year = getYear(date);
			long month = getMonth(date);
			long day = getDay(date);

			thisDate = date % HOUR;
			thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
					+ thisDate;

			return thisDate;
		}

		public  long setHour(long date, String hour) {
			long thisHour = 0;
			try {
				thisHour = Long.parseLong(hour);
			} catch (Exception e) {
				return date;
			}

			return setHour(date, thisHour);

		}

		public  long getHour(long date) {
			long hour = 0;
			hour = date % DAY;
			hour = hour / HOUR;
			return hour;
		}
		
		public  String getHour() {
			Calendar c = Calendar.getInstance();
			return towStr(c.get(Calendar.HOUR_OF_DAY));
		}

		public  long setMinute(long date, long minute) {
			long thisDate = 0;
			long year = getYear(date);
			long month = getMonth(date);
			long day = getDay(date);
			long hour = getHour(date);

			thisDate = date % MINUTE;
			thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
					+ minute * MINUTE + thisDate;

			return thisDate;
		}

		public  long setMinute(long date, String minute) {
			long thisMinute = 0;
			try {
				thisMinute = Long.parseLong(minute);
			} catch (Exception e) {
				return date;
			}

			return setMinute(date, thisMinute);
		}

		public  long getMinute(long date) {
			long minute = 0;

			minute = date % HOUR;
			minute = minute / MINUTE;

			return minute;
		}

		public  long setSecond(long date, long second) {
			long thisDate = 0;
			long year = getYear(date);
			long month = getMonth(date);
			long day = getDay(date);
			long hour = getHour(date);
			long minute = getMinute(date);

			thisDate = year * YEAR + month * MONTH + day * DAY + hour * HOUR
					+ minute * MINUTE + second;

			return thisDate;
		}

		public  long setSecond(long date, String second) {
			long thisSecond = 0;
			try {
				thisSecond = Long.parseLong(second);
			} catch (Exception e) {
				return date;
			}

			return setSecond(date, thisSecond);
		}

		public  long getSecond(long date) {
			long second = 0;
			second = date % MINUTE;

			return second;
		}
	/**
	 * 字符串格式化日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parse(String dateStr, SimpleDateFormat format) {
		if (dateStr == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = format == null ? standard_format
					: format;
			return _format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date parseDate(Date date) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat _format =  standard_format ;
			String dateStr = DateUtil.format(date);
			return _format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期格式化字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, SimpleDateFormat format) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = format == null ? standard_format
					: format;
			return _format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 字符串格式化日期
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parse(String dateStr, String format) {
		if (dateStr == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = StringUtils.isBlank(format) ? standard_format
					: new SimpleDateFormat(format);
			return _format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 日期格式化字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}
		try {
			SimpleDateFormat _format = StringUtils.isBlank(format) ? standard_format
					: new SimpleDateFormat(format);
			return _format.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取n天之后的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		return date == null ? null : DateUtils.addDays(date, days);
	}

	/**
	 * 时间格式化
	 * 
	 * @param pattern
	 * @param time
	 * @return
	 */
	public static String formatTime(String pattern, Date time) {
		return getFormatter(pattern).format(time);
	}

	/**
	 * 获取格式化formatter
	 * 
	 * @param pattern
	 * @return
	 */
	private static DateFormat getFormatter(String pattern) {
		DateFormat df = FormatterPool.get(pattern);
		if (df == null) {
			df = new SimpleDateFormat(pattern);
			FormatterPool.put(pattern, df);
		}
		return df;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获取当前时间
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getCurrentTime(String pattern) {
		return formatTime(pattern, DateUtil.getCurrentTime());
	}
	   public  static Date longToDate(Long param) {
	        Calendar cld = null;
	        if (cld == null) {
	            cld = new GregorianCalendar();
	        }
	        if (param != null) {
	            cld.clear();
	            cld.setTimeInMillis(param);
	            Date d = cld.getTime();
	            return d;
	        } else {
	            return null;
	        }
	    }

		public  static String format(Date date) {

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return c.get(Calendar.YEAR) + "-" + towStr(c.get(Calendar.MONTH) + 1)
					+ "-" + towStr(c.get(Calendar.DATE));

		}

		public static String format(long date) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date(date));
			return c.get(Calendar.YEAR) + "-" + towStr(c.get(Calendar.MONTH) + 1)
					+ "-" + towStr(c.get(Calendar.DATE));

		}

		public static String formatLongDate(long date) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date(date));
			return c.get(Calendar.YEAR) + "-" + towStr(c.get(Calendar.MONTH) + 1)
					+ "-" + towStr(c.get(Calendar.DATE)) + " " + towStr(c.get(Calendar.HOUR))
					+ ":" + towStr(c.get(Calendar.MINUTE))+ ":" + towStr(c.get(Calendar.SECOND));

		}
		
		public static  String towStr(int n) {
			if (n < 10)
				return "0" + n;
			else
				return "" + n;
		}
		public  long getDateTime(String someDate) {
			long date = 0;

			String format = "yyyy-MM-dd";
			if (someDate.length() > 10) {
				format = "yyyy-MM-dd HH:mm:ss";
			}
			SimpleDateFormat dd = new SimpleDateFormat(format);
			Date nowDate = new Date();
			try {
				nowDate = dd.parse(someDate);
			} catch (ParseException e) {
				throw new UtilException(e);
			}

			// Date nowDate = someDate;
			DateFormat df = DateFormat.getDateTimeInstance();
			String sDate = df.format(nowDate);
			int start = 0;
			int end = 0;
			String year = null;
			String month = null;
			String day = null;
			String hour = null;
			String minute = null;
			String second = null;
			end = sDate.indexOf("-", start);
			if (end > 0) {
				year = sDate.substring(start, end);
			}
			start = end + 1;
			end = sDate.indexOf("-", start);
			if (end > 0) {
				month = sDate.substring(start, end);
			}

			start = end + 1;
			end = sDate.indexOf(" ", start);
			if (end > 0) {
				day = sDate.substring(start, end);
			}

			start = end + 1;
			end = sDate.indexOf(":", start);
			if (end > 0) {
				hour = sDate.substring(start, end);
			}

			start = end + 1;
			end = sDate.indexOf(":", start);
			if (end > 0) {
				minute = sDate.substring(start, end);
			}

			start = end + 1;
			second = sDate.substring(start);

			date = setYear(date, year);
			date = setMonth(date, month);
			date = setDay(date, day);
			date = setHour(date, hour);
			date = setMinute(date, minute);
			date = setSecond(date, second);
			return date;
		}
	public static void main(String[] args) throws ParseException {
		System.out.println(DateUtil.format(new Date(), "dd"));
	}
}

package cn.zzjh.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * 日期帮助类
 * 
 * @author liumh
 *
 */
public class DateUtil {
	
	
	/**
	 * 指定日期传字符串
	 * 返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate(Date date) throws IOException {
    	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
    }
	
	
	/**
	 * 获取day天后的日期
	 * 
	 * @param day
	 *            正数为当前日期之后
	 * @return
	 */
	public static String getDayDateValue(int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(getDayDate(day));
	}

	/**
	 * 获取day天后的日期
	 * 
	 * @param day
	 *            正数为当前日期之后
	 * @return
	 */
	public static Date getDayDate(int day) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, day);
		return c.getTime();
	}

	/**
	 * 获取month月后的日期
	 * 
	 * @param f
	 *            是否从1号开始计算
	 * @return
	 */
	public static String getMonthDateValue(int month, boolean f) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(getMonthDate(month, f));

	}

	/**
	 * 获取month月后的日期
	 * 
	 * @param month
	 *            正数为当月之后
	 * @param f
	 *            是否从月初开始计算
	 * @return
	 */
	public static Date getMonthDate(int month, boolean f) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, month);
		if (f) {
			c.set(Calendar.DAY_OF_MONTH, 1);
		}

		return c.getTime();

	}

	/**
	 * 获取week周后的日期
	 * 
	 * @param week
	 *            正数为当前周之后
	 * @param f
	 *            是否从周一开始计算
	 * @return
	 */
	public static Date getWeekDate(int week, boolean f) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.add(Calendar.WEEK_OF_MONTH, week);
		if (f) {
			c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		}
		return c.getTime();
	}

	/**
	 * 获取week周后的日期
	 * 
	 * @param week
	 *            正数为当前周之后
	 * @param f
	 *            是否从周一开始计算
	 * @return
	 */
	public static String getWeekDateValue(int week, boolean f) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(getWeekDate(week, f));
	}

	/**
	 * 获取开始和结束时间中间 所有的天数的日期
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @param calendarType
	 * @return
	 */
	public static Date[] getDateArrays(Date start, Date end, int calendarType) {
		ArrayList<Date> ret = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		Date tmpDate = calendar.getTime();
		long endTime = end.getTime();
		while (tmpDate.before(end) || tmpDate.getTime() == endTime) {
			ret.add(calendar.getTime());
			calendar.add(calendarType, 1);
			tmpDate = calendar.getTime();
		}
		Date[] dates = new Date[ret.size()];
		return (Date[]) ret.toArray(dates);
	}
	
			/**
		 * 制定时间加上分钟数
		 * 		返回相加后的     年月日时分秒
		 * @param da	制定时间
		 * @param main  分钟数
		 * @return yyyy-MM-dd HH:mm
		 */
		public static Date timePlus(Date today,int main) {
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(today); 
			cal.add(Calendar.MINUTE,main);	//制定分钟 
			Date date=cal.getTime(); 
			return date;
		}
		
		
		/**
		 * 比较两个时间大小
		 * 	dt1 > d2  true	
		 *  dt1	< d2  false 
		 */
		public static boolean compareDate(Date dt1, Date dt2) {
            if (dt1.getTime() > dt2.getTime()) {
                return true;	//dt1 在dt2前
            } else if (dt1.getTime() < dt2.getTime()) {
                return false;	//dt1在dt2后
            } else {
                return false;
            }
	    }
		
		
		
	  /**
	   * 获取指定时间段中多少个周六日		王海文
	   * @param startDate	开始时间
	   * @param endDate		结束时间
	   * @param format	时间格式
	   * @return	
	 * @throws ParseException 
	   */
	  
	  public static int getSundayNum(String startDate, String endDate, String format) throws Exception{
		 int num=0;//周六，周日的总天数
		 Calendar c_begin = new GregorianCalendar();
	     Calendar c_end = new GregorianCalendar();
	     DateFormatSymbols dfs = new DateFormatSymbols(); 
	     String[] weeks = dfs.getWeekdays();
	     
	     SimpleDateFormat df = new SimpleDateFormat(format);
	     c_begin.setTime(df.parse(startDate));	//开始日期
	     c_end.setTime(df.parse(endDate));		//结束日期
	     c_end.add(Calendar.DAY_OF_YEAR, 1);  //结束日期下滚一天是为了包含最后一天
	     
	     try {
			while(c_begin.before(c_end)){
				 
				 Date date = new java.sql.Date(c_begin.getTime().getTime());
				 String request = request(date.toString());
				 
				 if(request != null && request.equals("1")) {
					 num += 1;
				 }
			    c_begin.add(Calendar.DAY_OF_YEAR, 1);
			 }
		} catch (Exception e) {
			//如果接口不同，默认按周六日计算
			while(c_begin.before(c_end)){
			 	String weekDay = weeks[c_begin.get(Calendar.DAY_OF_WEEK)].substring(2, 3);
			 	if(weekDay.equals("六") || weekDay.equals("日")) {
			 		num += 1;
			 	}
			    c_begin.add(Calendar.DAY_OF_YEAR, 1);
			 }
			e.printStackTrace();
		}
		  return num;
	  }
	  
	    /**
	     * 获取某个日期是星期几		王海文
	     * @param date	传入时间
	     * @param format 日期格式 yyyy-MM-dd HH:mm:ss
	     * @return 0-星期日
	     */
	    public static int getWeek(String date, String format) {
	        Calendar calendarTemp = Calendar.getInstance();
	        try {
	            calendarTemp.setTime(new SimpleDateFormat(format).parse(date));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        int i = calendarTemp.get(Calendar.DAY_OF_WEEK);
	        int value=i-1;//0-星期日
	        return value;
	    }
		
	    
		/**
	     * 调用百度接口 查询制定日期是否是国定放假日	王海文
	     * @param httpArg
	     * @return
	     * @throws IOException 
	     */
		public static String request(String httpArg) throws IOException {
			 String httpUrl = "http://api.goseek.cn/Tools/holiday";
//			String httpUrl = "http://api.k780.com/?app=life.workday&date=" + httpArg
//					+ "&appkey=32389&sign=a6dde06d358793b4dede7901770442a2&format=json";
			BufferedReader reader = null;
			String result = null;
			StringBuffer sbf = new StringBuffer();
			httpUrl = httpUrl + "?date=" + httpArg;

			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			connection.setConnectTimeout(2000);
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
			Map<String, Object> map = MapUtils.toHashMap(result);
//			JSONObject jsob = JSONObject.parseObject(map.get("result").toString());
//			String data = jsob.get("workmk").toString();
			String data = String.valueOf(map.get("data"));
			return map != null ? data : null;
		}
		
}
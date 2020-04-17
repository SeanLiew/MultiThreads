package utils;



import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtilsCon {


    public static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";
    public static final int TIME_DAY_MILLISECOND = 24 * 60 * 60 * 1000;
    public static final int TIME_DAY_MILLIHOUR = 60 * 60 * 1000;
    public static final int TIME_DAY_MILLIMINUS = 60 * 1000;
    private static final int RATIO = 1000;
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";

    public static final String MONTH_DAY_FORMAT = "MM-dd";

    public static final String DATE_FORMAT_MM_dd_HH_mm = "MM月dd日 HH:mm";

    public static Date addDays(Date date, int amount) {
        return addDay(date, Calendar.DAY_OF_MONTH, amount);
    }



    /**
     * 取得某日期时间的特定表示格式的字符串
     *
     * @param format 时间格式
     * @param date   某日期（Date）
     * @return 某日期的字符串
     */
    public static synchronized String getDate2Str(String format, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat.format(date);
    }

    public static synchronized String getMillisStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:sss");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 转换日期
     * @return
     */
    public static Date parseDay(String dateStr,String format){
        try {
            DateFormat df = new SimpleDateFormat(format);
            return df.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static Date addDay(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 按yyyy-MM-ddTHH:mm:ss格式转换成Date ,并去掉时分秒
     *
     * @param str
     * @return
     */
    public static synchronized Date strToDateByT(String str) {
        if (str.length() < 10) {
            return null;
        } else {
            return strToDate("yyyy-MM-dd", str.substring(0, 10));
        }

    }


    /**
     * 将特定格式的时间字符串转化为Date类型
     *
     * @param format 时间格式
     * @param str    某日期的字符串
     * @return 某日期（Date）
     */
    public static synchronized Date strToDate(String format, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern(format);
        ParsePosition parseposition = new ParsePosition(0);
        return simpleDateFormat.parse(str, parseposition);
    }

    public static Date strToDateTime(String str) {
        return strToDate(DEFAULT_DATE_FORMATE, str);
    }

    public static Date strToDate(String str) {
        return strToDate(DATE_FORMAT, str);
    }

    public static String dateTimeToString(Date date) {
        return getDate2Str(DEFAULT_DATE_FORMATE, date);
    }

    public static String dateToString(Date date) {
        return getDate2Str(DATE_FORMAT, date);
    }

    public static String dateToTipString(Date date) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        return  String.format("%02d",day);
      //  return getDate2Str(DATE_FORMAT_MM_dd_HH_mm, calendar.getTime());
    }


    public static String dateToSmsPushTimeString(Date date) {
        return getDate2Str(DATE_FORMAT_MM_dd_HH_mm, date);
    }

    public static String dateToyyyyMMdd(Date date)
    {
        return  getDate2Str(DATE_FORMAT_yyyyMMdd, date);
    }
    public static void main(String[] args) {
        System.out.println(dateToTipString(new Date()));
    }


    public static String date2String1(Date date) {
        return getDate2Str(DATE_FORMAT, date);
    }


    /**
     * 检测字符串是否为日期
     *
     * @param dateTime 时间字符串
     * @param pattern  Eg "yyyy-MM-dd"
     * @return 返回结果
     */
    public static boolean isDateTime(String dateTime, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        ParsePosition pos = new ParsePosition(0);
        Date dt = df.parse(dateTime, pos);
        return !(dt == null);
    }



    public static Date convertToDate(XMLGregorianCalendar cal) {
        GregorianCalendar ca = cal.toGregorianCalendar();
        return ca.getTime();
    }

    public boolean passTime(Date tempDate, int hour) {
        return !(tempDate == null || hour <= 0) && tempDate.before(getLimitDate(hour));
    }

    /**
     * 得到n小时前时间
     *
     * @param hour 小时数
     * @return Date
     */
    private Date getLimitDate(int hour) {
        Calendar cl = Calendar.getInstance();
        Long clTemp = cl.getTimeInMillis() - hour * 60 * 60 * 1000;
        cl.setTimeInMillis(clTemp);
        return cl.getTime();
    }

    /**
     * 比较两个时间
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1, Date date2) {
        boolean flag = false;
        if (date1.after(date2)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 通过日期获取当天是星期几
     *
     * @param date
     * @return
     */
    public static String date2Week(Date date) {
        String[] weekDays = {"\u661F\u671F\u65E5", "\u661f\u671f\u4e00", "\u661f\u671f\u4e8c", "\u661f\u671f\u4e09", "\u661f\u671f\u56db", "\u661f\u671f\u4e94", "\u661f\u671f\u516d"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 1,2,3 ..
     * 返回周几（1：周一，7：周日）
     *
     * @param date
     * @return
     */
    public static String date2WeekNum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) {
            return "7";
        }
        return String.valueOf(w);
    }

    /**
     * @param date
     * @return 周一...
     */
    public static String date2WeekCnNum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) {
            w = 7;
        }
        return weekCn(String.valueOf(w));
    }

    private static String weekCn(String w) {
        if ("1".equals(w)) {
            return "周一";
        } else if ("2".equals(w)) {
            return "周二";
        } else if ("3".equals(w)) {
            return "周三";
        } else if ("4".equals(w)) {
            return "周四";
        } else if ("5".equals(w)) {
            return "周五";
        } else if ("6".equals(w)) {
            return "周六";
        } else {
            return "周日";
        }
    }

    /**
     * 返回 2012年8月29日
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static String date2StringAndWeekNum(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Integer[] da = new Integer[3];
        da[0] = c.get(Calendar.YEAR);
        da[1] = c.get(Calendar.MONTH) + 1;
        da[2] = c.get(Calendar.DATE);
        return c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月" + c.get(Calendar.DATE) + "日";
    }

    /**
     * 月-日
     * 返回 8-29
     *
     * @param date
     * @return
     */
    public static String date2MonthAndWeekNum(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Integer[] da = new Integer[3];
        da[0] = c.get(Calendar.YEAR);
        da[1] = c.get(Calendar.MONTH) + 1;
        da[2] = c.get(Calendar.DATE);
        return (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE);
    }

    /**
     * 返回 8月-29日
     *
     * @param date
     * @return
     */
    public static String date2CnMonthAndWeekNum(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Integer[] da = new Integer[3];
        da[0] = c.get(Calendar.YEAR);
        da[1] = c.get(Calendar.MONTH) + 1;
        da[2] = c.get(Calendar.DATE);
        return (c.get(Calendar.MONTH) + 1) + "月" + c.get(Calendar.DATE) + "日";
    }


    public static String date2WeekForString(String date) {
        if (date != null && !date.trim().equals("0")) {
            return date2Week(new Date(Long.valueOf(date.trim()) * 1000));
        }
        return null;
    }



    /**
     * 字符串日期转换为查询的开始日期
     *
     * @param dateStr yyyy-MM-dd
     * @return
     */
    public static long str2startTime(String dateStr) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateTimeFormat
                    .parse(dateStr + " 00:00:00")
                    .getTime() / RATIO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串日期转换为查询的截止日期
     *
     * @param dateStr yyyy-MM-dd
     * @return
     */
    public static long str2endTime(String dateStr) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateTimeFormat
                    .parse(dateStr + " 23:59:59")
                    .getTime() / RATIO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串日期转换为查询的开始日期
     *
     * @param dateStr yyyyMMdd
     * @return
     */
    public static String str2startDateStr(String dateStr) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return getFormatDateTime(dateTimeFormat.parse(dateStr + "000000"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串日期转换为查询的截止日期
     *
     * @param dateStr yyyyMMdd
     * @return
     */
    public static String str2endDateStr(String dateStr) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return getFormatDateTime(dateTimeFormat.parse(dateStr + "235959"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate 要格式化的日期
     * @param format   日期格式，如yyyy-MM-dd
     * @return Date 返回格式化后的日期，格式由参数<code>format</code>
     *         定义，如yyyy-MM-dd，如2006-02-15
     * @see SimpleDateFormat#parse(String)
     */
    public static Date getFormatDate(String currDate, String format) {
        if (currDate == null) {
            return null;
        }
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.parse(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.parse(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    /**
     * 根据格式得到格式化后的时间
     *
     * @param currDate 要格式化的时间
     * @param format   时间格式，如yyyy-MM-dd HH:mm:ss
     * @return String 返回格式化后的时间，格式由参数<code>format</code>定义，如yyyy-MM-dd
     *         HH:mm:ss
     * @see SimpleDateFormat#format(Date)
     */
    public static String getFormatDateTime(Date currDate, String format) {
        if (currDate == null) {
            return "";
        }
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DEFAULT_DATE_FORMATE);
            try {
                return dtFormatdB.format(currDate);
            } catch (Exception ex) {
            }
        }
        return "";
    }

    /**
     * 根据格式得到格式化后的日期
     *
     * @param currDate 要格式化的日期
     * @param format   日期格式，如yyyy-MM-dd
     * @return String 返回格式化后的日期，格式由参数<code>format</code>
     *         定义，如yyyy-MM-dd，如2006-02-15
     * @see SimpleDateFormat#format(Date)
     */
    public static String getFormatDate(Date currDate, String format) {
        if (currDate == null) {
            return "";
        }
        SimpleDateFormat dtFormatdB = null;
        try {
            dtFormatdB = new SimpleDateFormat(format);
            return dtFormatdB.format(currDate);
        } catch (Exception e) {
            dtFormatdB = new SimpleDateFormat(DATE_FORMAT);
            try {
                return dtFormatdB.format(currDate);
            } catch (Exception ex) {
            }
        }
        return null;
    }

    /**
     * 得到格式化后的时间，格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     *
     * @param currDate 要格式化的时间
     * @return String 返回格式化后的时间，默认格式为yyyy-MM-dd HH:mm:ss，如2006-02-15 15:23:45
     * @see #getFormatDateTime(Date, String)
     */
    public static String getFormatDateTime(Date currDate) {
        return getFormatDateTime(currDate, DEFAULT_DATE_FORMATE);
    }


    /**
     * 取得当前系统时间，返回java.util.Date类型
     *
     * @return java.util.Date 返回服务器当前系统时间
     * @see Date
     */
    public static Date getCurrDate() {
        return new Date();
    }

    public static String getCurrDateStr() {
        return dateTimeToString(new Date());
    }

    /**
     * 取得当前系统时间戳
     *
     * @return java.sql.Timestamp 系统时间戳
     * @see Timestamp
     */
    public static Timestamp getCurrTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }


    /**
     * 得到本日的上月时间 如果当日为2007-9-1,那么获得2007-8-1
     */
    public static String getDateBeforeMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到本日的前几个月时间 如果number=2当日为2007-9-1,那么获得2007-7-1
     */
    public static String getDateBeforeDay(int number) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -number);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }

    /**
     * 得到几天前的时间
     */
    public static String getDateBeforeMonth(int number) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -number);
        return getFormatDate(cal.getTime(), DATE_FORMAT);
    }


    /**
     * 获得两个Date型日期之间相差的天数（第2个减第1个）
     *
     * @param first
     * @param second
     * @return int 相差的天数
     */
    public static int getDaysBetweenDates(Date first, Date second) {
        Long mils = (second.getTime() - first.getTime()) / (TIME_DAY_MILLISECOND);
        return mils.intValue();
    }

    /**
     * 获得两个Date型日期之间相差的小时（第2个减第1个）
     *
     * @param first
     * @param second
     * @return int 相差的小时
     */
    public static int getHoursBetweenDays(Date first, Date second) {
        Long mils = (second.getTime() - first.getTime()) / (TIME_DAY_MILLIHOUR);
        return mils.intValue();
    }

    /**
     * 获得两个Date型日期之间相差的分钟（第2个减第1个）
     *
     * @param first
     * @param second
     * @return int 相差的小时
     */
    public static int getMinusBetweenDays(Date first, Date second) {
        Long mils = (second.getTime() - first.getTime()) / (TIME_DAY_MILLIMINUS);
        return mils.intValue();
    }

    /**
     * 比较2个日期大小
     * date2 > date1 return 1
     * date2 < date1 return -1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compareTime(Date date1, Date date2) {
        return date2.compareTo(date1);
    }

    //比较时间(当天的小时)
    public static boolean compareTime(Date nowDate, String minute) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String subDate = dateTimeFormat.format(nowDate).substring(0, 10);
        try {
            long nowTime = nowDate.getTime();
            long minuteTime = dateTimeFormat.parse(subDate + " " + minute).getTime();
            if (nowTime - minuteTime > 0) {
                return true;
            }
        } catch (ParseException e) {

        }
        return false;
    }

    //比较两个分钟大小
    public static boolean compareTimeMi(Date nowDate, String start, String end) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //Date nowDate = new Date();
        String subDate = dateTimeFormat.format(nowDate).substring(0, 10);
        try {
            long st = dateTimeFormat.parse(subDate + " " + start).getTime();
            long en = dateTimeFormat.parse(subDate + " " + end).getTime();
            if (en - st > 0) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    //比较两个分钟大小
    public static Date getNexDay(Integer days) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        Date mydate = null;
        try {
            mydate =  sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mydate;
    }


    //比较两个分钟大小
    public static Date getNexDay(Date date,Integer days) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        Date mydate = null;
        try {
            mydate =  sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mydate;
    }
    /**
     * 转成日期字符串，"MM-dd"
     * @return
     */
    public static String getMonthDay(Date date){
        if (date == null){
            return null;
        }
        DateFormat simpleDateFormat = new SimpleDateFormat(MONTH_DAY_FORMAT);
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间戳转换成指定格式的时间字符串
     * @param timgLong
     * @param format
     * @return
     */
    public static String convertTimestampToFormatTime(Long timgLong,String format){
        if (timgLong == null){
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timgLong), ZoneId.systemDefault());
        return DateTimeFormatter.ofPattern(format).format(localDateTime);
    }
}

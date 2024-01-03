import org.junit.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTest {
    @Test
    public void test1() {
        Date currDate = new Date();
        System.out.println(currDate.toString());
        // 已经@Deprecated
        System.out.println(currDate.toLocaleString());
        // 已经@Deprecated
        System.out.println(currDate.toGMTString());
    }

    @Test
    public void test2() {
        String patternStr = "yyyy-MM-dd HH:mm:ss";
        // 北京时间（new出来就是默认时区的时间）
        Date bjDate = new Date();

        // 得到纽约的时区
        TimeZone newYorkTimeZone = TimeZone.getTimeZone("America/New_York");
        // 根据此时区 将北京时间转换为纽约的Date
        DateFormat newYorkDateFormat = new SimpleDateFormat(patternStr);
        newYorkDateFormat.setTimeZone(newYorkTimeZone);
        System.out.println("这是北京时间：" + new SimpleDateFormat(patternStr).format(bjDate));
        System.out.println("这是纽约时间：" + newYorkDateFormat.format(bjDate));
    }

    @Test
    public void test3() {
        String[] availableIDs = TimeZone.getAvailableIDs();
        System.out.println("可用zoneId总数：" + availableIDs.length);
        for (String zoneId : availableIDs) {
            System.out.println(zoneId);
        }
    }


    /**
     * 倘若时区不同，那么势必影响到程序的运行结果，很容易带来计算逻辑的错误，很可能就乱套了。Java让我们有多种方式可以手动设置/修改默认时区：
     *
     * API方式：强制将时区设为北京时区TimeZone.setDefault(TimeZone.getDefault().getTimeZone("GMT+8"));
     * JVM参数方式：-Duser.timezone=GMT+8
     */
    @Test
    public void test4() {
        System.out.println(TimeZone.getTimeZone("GMT+08:00").getID());
        System.out.println(TimeZone.getDefault().getID());

        // 纽约时间
        System.out.println(TimeZone.getTimeZone("GMT-05:00").getID());
        System.out.println(TimeZone.getTimeZone("America/New_York").getID());
    }

    @Test
    public void test5() throws ParseException {
        String patterStr = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(patterStr);

        String birthdayStr = "1988-09-11";
        // 字符串 -> Date -> 字符串
        Date birthday = dateFormat.parse(birthdayStr);
        long birthdayTimestamp = birthday.getTime();
        System.out.println("老王的生日是：" + birthday);
        System.out.println("老王的生日的时间戳是：" + birthdayTimestamp);

        System.out.println("==============程序经过一番周转，我的同时 方法入参传来了生日的时间戳=============");
        // 字符串 -> Date -> 时间戳 -> Date -> 字符串
        birthday = new Date(birthdayTimestamp);
        System.out.println("老王的生日是：" + birthday);
        System.out.println("老王的生日的时间戳是：" + birthday.getTime());
    }

    /**
     * Date对象里存的是自格林威治时间（ GMT）1970年1月1日0点至Date所表示时刻所经过的毫秒数
     */
    @Test
    public void test6() {
        String patterStr = "yyyy-MM-dd HH:mm:ss";
        Date currDate = new Date(System.currentTimeMillis());

        // 北京时区
        DateFormat bjDateFormat = new SimpleDateFormat(patterStr);
        bjDateFormat.setTimeZone(TimeZone.getDefault());
        // 纽约时区
        DateFormat newYorkDateFormat = new SimpleDateFormat(patterStr);
        newYorkDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        // 伦敦时区
        DateFormat londonDateFormat = new SimpleDateFormat(patterStr);
        londonDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        System.out.println("毫秒数:" + currDate.getTime() + ", 北京本地时间:" + bjDateFormat.format(currDate));
        System.out.println("毫秒数:" + currDate.getTime() + ", 纽约本地时间:" + newYorkDateFormat.format(currDate));
        System.out.println("毫秒数:" + currDate.getTime() + ", 伦敦本地时间:" + londonDateFormat.format(currDate));
    }

    @Test
    public void test7() throws ParseException {
        String patterStr = "yyyy-MM-dd HH:mm:ss";

        // 模拟请求参数的时间字符串
        String dateStrParam = "2020-01-15 18:00:00";

        // 模拟服务端对此服务换转换为Date类型
        DateFormat dateFormat = new SimpleDateFormat(patterStr);
        System.out.println("格式化器用的时区是：" + dateFormat.getTimeZone().getID());
        Date date = dateFormat.parse(dateStrParam);
        System.out.println(date);
    }

    @Test
    public void test9() throws ParseException {
        String patternStr = "G GG GGGGG E EE EEEEE a aa aaaaa";
        Date currDate = new Date();

        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓中文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        System.out.println("====================Date->String====================");
        DateFormat dateFormat = new SimpleDateFormat(patternStr, Locale.CHINA);
        System.out.println(dateFormat.format(currDate));

        System.out.println("====================String->Date====================");
        String dateStrParam = "公元 公元 公元 星期六 星期六 星期六 下午 下午 下午";
        System.out.println(dateFormat.parse(dateStrParam));

        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓英文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        System.out.println("====================Date->String====================");
        dateFormat = new SimpleDateFormat(patternStr, Locale.US);
        System.out.println(dateFormat.format(currDate));

        System.out.println("====================String->Date====================");
        dateStrParam = "AD ad bC Sat SatUrday sunDay PM PM Am";
        System.out.println(dateFormat.parse(dateStrParam));
    }

    @Test
    public void test10() {
        long currMillis = System.currentTimeMillis();

        java.util.Date date = new Date(currMillis);
        java.sql.Date sqlDate = new java.sql.Date(currMillis);
        java.sql.Time time = new Time(currMillis);
        java.sql.Timestamp timestamp = new Timestamp(currMillis);

        System.out.println("java.util.Date：" + date);
        System.out.println("java.sql.Date：" + sqlDate);
        System.out.println("java.sql.Time：" + time);
        System.out.println("java.sql.Timestamp：" + timestamp);
    }
}

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 因为有夏令时规则的存在，让操作日期/时间的复杂度大大增加。但还好JDK尽量的屏蔽了这些规则对使用者的影响。因此：推荐使用时区（ZoneId）转换日期/时间，一般情况下不建议使用偏移量ZoneOffset去搞，这样就不会有夏令时的烦恼啦。
 */
public class ZoneIdTest {
    @Test
    public void test1() {
        // JDK 1.8之前做法
        System.out.println(TimeZone.getDefault());
        // JDK 1.8之后做法
        System.out.println(ZoneId.systemDefault());
    }

    @Test
    public void test2() {
        System.out.println(ZoneId.of("Asia/Shanghai"));
        // 报错：java.time.zone.ZoneRulesException: Unknown time-zone ID: Asia/xxx
        System.out.println(ZoneId.of("Asia/xxx"));
    }


    @Test
    public void test3() {
        ZoneId.getAvailableZoneIds();
    }

    /**
     * 这里第一个参数传的前缀，可用值为："GMT", "UTC", or "UT"。当然还可以传空串，那就直接返回第二个参数ZoneOffset。若以上都不是就报错
     * <p>
     * 注意：根据偏移量得到的ZoneId内部并无现成时区规则可用，因此对于有夏令营的国家转换可能出问题，一般不建议这么去做。
     */
    @Test
    public void test4() {
        ZoneId zoneId = ZoneId.ofOffset("UTC", ZoneOffset.of("+8"));
        System.out.println(zoneId);
        // 必须是大写的Z
        zoneId = ZoneId.ofOffset("UTC", ZoneOffset.of("Z"));
        System.out.println(zoneId);
    }

    @Test
    public void test5() {
        System.out.println(ZoneId.from(ZonedDateTime.now()));
        System.out.println(ZoneId.from(ZoneOffset.of("+8")));

        // 虽然方法入参是TemporalAccessor，但是只接受带时区的类型，LocalXXX是不行的，使用时稍加注意。
        // 报错：java.time.DateTimeException: Unable to obtain ZoneId from TemporalAccessor:
        System.out.println(ZoneId.from(LocalDateTime.now()));
        System.out.println(ZoneId.from(LocalDate.now()));
    }

    @Test
    public void test6() {
        System.out.println("最小偏移量：" + ZoneOffset.MIN);
        System.out.println("最小偏移量：" + ZoneOffset.MAX);
        System.out.println("中心偏移量：" + ZoneOffset.UTC);
        // 超出最大范围
        System.out.println(ZoneOffset.of("+20"));
    }

    @Test
    public void test7() {
        System.out.println(ZoneOffset.ofHours(8));
        System.out.println(ZoneOffset.ofHoursMinutes(8, 8));
        System.out.println(ZoneOffset.ofHoursMinutesSeconds(8, 8, 8));

        System.out.println(ZoneOffset.ofHours(-5));

        // 指定一个精确的秒数  获取实例（有时候也很有用处）
        System.out.println(ZoneOffset.ofTotalSeconds(8 * 60 * 60));
    }

    @Test
    public void test8() {
        // 本地日期/时间
        System.out.println("================本地时间================");
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        // 时区时间
        System.out.println("================带时区的时间ZonedDateTime================");
        System.out.println(ZonedDateTime.now()); // 使用系统时区
        System.out.println(ZonedDateTime.now(ZoneId.of("America/New_York"))); // 自己指定时区
        System.out.println(ZonedDateTime.now(Clock.systemUTC())); // 自己指定时区
        System.out.println("================带时区的时间OffsetDateTime================");
        System.out.println(OffsetDateTime.now()); // 使用系统时区
        System.out.println(OffsetDateTime.now(ZoneId.of("America/New_York"))); // 自己指定时区
        System.out.println(OffsetDateTime.now(Clock.systemUTC())); // 自己指定时区
    }

    @Test
    public void test11() {
        String dateTimeStrParam = "2021-05-05T18:00";
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStrParam);
        System.out.println("解析后：" + localDateTime);
    }

    @Test
    public void test12() {
        // 带偏移量 使用OffsetDateTime
        String dateTimeStrParam = "2021-05-05T18:00-04:00";
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTimeStrParam);
        System.out.println("带偏移量解析后：" + offsetDateTime);

        // 带时区 使用ZonedDateTime
        //在2021.03.14 - 2021.11.07期间，纽约的偏移量是-4，其余时候是-5。本例的日期是2021-05-05处在夏令时之中，因此偏移量是-4，这就解释了为何你显示的写了-5最终还是成了-4。
        dateTimeStrParam = "2021-05-05T18:00-05:00[America/New_York]";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTimeStrParam);
        System.out.println("带时区解析后：" + zonedDateTime);
    }

    @Test
    public void test13() {
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.now()));
        System.out.println(DateTimeFormatter.ISO_LOCAL_TIME.format(LocalTime.now()));
        System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
    }

    @Test
    public void test14() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("第Q季度 yyyy-MM-dd HH:mm:ss", Locale.US);

        // 格式化输出
        String format = formatter.format(LocalDateTime.now());
        System.out.println(format);

        // 解析
        LocalDateTime localDateTime = LocalDateTime.parse(format, formatter);
        System.out.println("解析后的结果：" + localDateTime);
    }

    /**
     * 永远显式的指定你需要的时区，即使你要获取的是默认时区
     */
    @Test
    public void test15() {
        // 方式一：普通做法
        System.out.println(LocalDateTime.now());

        // 方式二：最佳实践
        System.out.println(LocalDateTime.now(ZoneId.systemDefault()));
    }

}

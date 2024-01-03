import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class DateTimeTest {

    @Test
    public void test1() {
        LocalDateTime min = LocalDateTime.MIN;
        LocalDateTime max = LocalDateTime.MAX;

        System.out.println("LocalDateTime最小值：" + min);
        System.out.println("LocalDateTime最大值：" + max);
        System.out.println(min.getYear() + "-" + min.getMonthValue() + "-" + min.getDayOfMonth());
        System.out.println(max.getYear() + "-" + max.getMonthValue() + "-" + max.getDayOfMonth());
    }


    @Test
    public void test2() {
        System.out.println("当前时区的本地时间：" + LocalDateTime.now());
        System.out.println("当前时区的本地时间：" + LocalDateTime.of(LocalDate.now(), LocalTime.now()));

        System.out.println("纽约时区的本地时间：" + LocalDateTime.now(ZoneId.of("America/New_York")));
    }

    @Test
    public void test3() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        System.out.println("计算前：" + now);

        // 加3天
        LocalDateTime after = now.plusDays(3);
        // 减4个小时
        after = after.plusHours(-3); // 效果同now.minusDays(3);
        System.out.println("计算后：" + after);

        // 计算时间差
        Period period = Period.between(now.toLocalDate(), after.toLocalDate());
        System.out.println("相差天数：" + period.getDays());
        Duration duration = Duration.between(now.toLocalTime(), after.toLocalTime());
        System.out.println("相差小时数：" + duration.toHours());
    }

    @Test
    public void test4() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault());
        // System.out.println("格式化输出：" + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now));
        System.out.println("格式化输出（本地化输出，中文环境）：" + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).format(now));

        String dateTimeStrParam = "2021-01-17 18:00:00";
        System.out.println("解析后输出：" + LocalDateTime.parse(dateTimeStrParam, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US)));
    }

    @Test
    public void test5() {
        OffsetDateTime min = OffsetDateTime.MIN;
        OffsetDateTime max = OffsetDateTime.MAX;

        System.out.println("OffsetDateTime最小值：" + min);
        System.out.println("OffsetDateTime最大值：" + max);
        System.out.println(min.getOffset() + ":" + min.getYear() + "-" + min.getMonthValue() + "-" + min.getDayOfMonth());
        System.out.println(max.getOffset() + ":" + max.getYear() + "-" + max.getMonthValue() + "-" + max.getDayOfMonth());
    }

    @Test
    public void test6() {
        System.out.println("当前位置偏移量的本地时间：" + OffsetDateTime.now());
        System.out.println("偏移量-4（纽约）的本地时间：：" + OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("-4")));

        System.out.println("纽约时区的本地时间：" + OffsetDateTime.now(ZoneId.of("America/New_York")));
    }

    @Test
    public void test7() {
        OffsetDateTime now = OffsetDateTime.now(ZoneId.systemDefault());
        System.out.println("格式化输出（本地化输出，中文环境）：" + DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).format(now));

        String dateTimeStrParam = "2021-01-17T18:00:00+07:00";
        System.out.println("解析后输出：" + OffsetDateTime.parse(dateTimeStrParam));
    }

    @Test
    public void test8() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 01, 17, 18, 00, 00);
        System.out.println("当前时区（北京）时间为：" + localDateTime);

        // 转换为偏移量为 -4的OffsetDateTime时间
        // 1、-4地方的晚上18点
        System.out.println("-4偏移量地方的晚上18点：" + OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(-4)));
        System.out.println("-4偏移量地方的晚上18点（方式二）：" + localDateTime.atOffset(ZoneOffset.ofHours(-4)));
        // 2、北京时间晚上18:00 对应的-4地方的时间点
        System.out.println("当前地区对应的-4地方的时间：" + OffsetDateTime.ofInstant(localDateTime.toInstant(ZoneOffset.ofHours(8)), ZoneOffset.ofHours(-4)));
    }

    @Test
    public void test81() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(-4));
        System.out.println("-4偏移量时间为：" + offsetDateTime);

        // 转为LocalDateTime 注意：时间还是未变的哦
        System.out.println("LocalDateTime的表示形式：" + offsetDateTime.toLocalDateTime());
    }

    @Test
    public void test9() {
        System.out.println("当前位置偏移量的本地时间：" + ZonedDateTime.now());
        System.out.println("纽约时区的本地时间：" + ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("America/New_York")));

        System.out.println("北京实现对应的纽约时区的本地时间：" + ZonedDateTime.now(ZoneId.of("America/New_York")));
    }

    @Test
    public void test10() {
        LocalDateTime localDateTime = LocalDateTime.of(2021, 01, 17, 18, 00, 00);
        System.out.println("当前时区（北京）时间为：" + localDateTime);

        // 转换为偏移量为 -4的OffsetDateTime时间
        // 1、-4地方的晚上18点
        System.out.println("纽约时区晚上18点：" + ZonedDateTime.of(localDateTime, ZoneId.of("America/New_York")));
        System.out.println("纽约时区晚上18点（方式二）：" + localDateTime.atZone(ZoneId.of("America/New_York")));
        // 2、北京时间晚上18:00 对应的-4地方的时间点
        System.out.println("北京地区此时间对应的纽约的时间：" + ZonedDateTime.ofInstant(localDateTime.toInstant(ZoneOffset.ofHours(8)), ZoneOffset.ofHours(-4)));
        System.out.println("北京地区此时间对应的纽约的时间：" + ZonedDateTime.ofInstant(localDateTime, ZoneOffset.ofHours(8), ZoneOffset.ofHours(-4)));
    }

    @Test
    public void test101() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(-4));
        System.out.println("-4偏移量时间为：" + offsetDateTime);

        // 转换为ZonedDateTime的表示形式
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.toZonedDateTime());
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSameInstant(ZoneId.of("America/New_York")));
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSimilarLocal(ZoneId.of("America/New_York")));
    }

    @Test
    public void test101_2() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(LocalDateTime.of(2021, 05, 05, 18, 00, 00), ZoneOffset.ofHours(-4));
        System.out.println("-4偏移量时间为：" + offsetDateTime);

        // 转换为ZonedDateTime的表示形式
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.toZonedDateTime());
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSameInstant(ZoneId.of("America/New_York")));
        System.out.println("ZonedDateTime的表示形式：" + offsetDateTime.atZoneSimilarLocal(ZoneId.of("America/New_York")));
    }



}

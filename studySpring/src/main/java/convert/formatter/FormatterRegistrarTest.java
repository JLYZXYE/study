package convert.formatter;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.format.datetime.standard.DateTimeContextHolder;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;

import java.time.*;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class FormatterRegistrarTest {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    public void test1() {
        FormattingConversionService conversionService = new FormattingConversionService();
        // 注册员负责添加格式化器以支持Date系列的转换
        new DateFormatterRegistrar().registerFormatters((FormatterRegistry) conversionService);

        // 1、普通使用
        long currMills = System.currentTimeMillis();
        System.out.println("当前时间戳：" + currMills);
        // Date -> Calendar
        System.out.println(conversionService.convert(new Date(currMills), Calendar.class));
        // Long ->  Date
        System.out.println(conversionService.convert(currMills, Date.class));
        // Calendar -> Long
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(currMills);
        System.out.println(conversionService.convert(calendar, Long.class));
    }

    public static void test2() {
        FormattingConversionService conversionService = new FormattingConversionService();
        // 注册员负责添加格式化器以支持Date系列的转换
        new DateFormatterRegistrar().registerFormatters((FormatterRegistry) conversionService);

        // 1、注解使用
        Son son = new Son(new Date());
        // 输出：将Date类型输出为Long类型
        System.out.println(conversionService.convert(son.getBirthday(), Long.class));
        // 输出：将String烈性输入为Date类型
//         System.out.println(conversionService.convert("2021-02-12", Date.class)); // 报错
        System.out.println(conversionService.convert(1613034123709L, Date.class));
    }

    public static void test3() {
        FormattingConversionService conversionService = new FormattingConversionService();
        // 注册员负责添加格式化器以支持Date系列的转换
        new DateTimeFormatterRegistrar().registerFormatters((FormatterRegistry) conversionService);

        // 1、普通使用(API方式)
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间：" + now);
        System.out.println("LocalDateTime转为LocalDate：" + conversionService.convert(now, LocalDate.class));
        System.out.println("LocalDateTime转为LocalTime：" + conversionService.convert(now, LocalTime.class));

        // 时间戳转Instant
        long currMills = System.currentTimeMillis();
        System.out.println("当前时间戳：" + currMills);
        System.out.println("时间戳转Instant：" + conversionService.convert(currMills, Instant.class));
    }

    public static final DateTimeFormatter GLOBAL_DATETIME_FORMATTER = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
            .withLocale(Locale.CHINA)
            .withZone(ZoneId.of("Asia/Shanghai"))
            .withChronology(IsoChronology.INSTANCE);

    public static void test4() throws InterruptedException {
        // 模拟请求参数(同一个参数，在不同接口里的不同表现)
        Instant start = Instant.now();

        // 模拟Controller的接口1：zoneId不一样
        new Thread(() -> {
            DateTimeContext context = new DateTimeContext();
            context.setTimeZone(ZoneId.of("America/New_York"));
            DateTimeContextHolder.setDateTimeContext(context);
            // 基于全局的格式化器 + 自己的上下文自定义一个本接口专用的格式化器
            DateTimeFormatter primaryFormatter = DateTimeContextHolder.getFormatter(GLOBAL_DATETIME_FORMATTER, null);

            System.out.printf("北京时间%s 接口1时间%s \n",
                    GLOBAL_DATETIME_FORMATTER.format(start),
                    primaryFormatter.format(start));
        }).start();

        // 模拟Controller的接口2：Locale不一样
        new Thread(() -> {
            // 基于全局的格式化器 + 自己的上下文自定义一个本接口专用的格式化器
            DateTimeFormatter primaryFormatter = DateTimeContextHolder.getFormatter(GLOBAL_DATETIME_FORMATTER, Locale.US);

            System.out.printf("北京时间%s 接口2时间%s \n",
                    GLOBAL_DATETIME_FORMATTER.format(start),
                    primaryFormatter.format(start));
        }).start();

        TimeUnit.SECONDS.sleep(2);
    }
}

package convert.formatter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.format.number.CurrencyStyleFormatter;
import org.springframework.format.number.NumberStyleFormatter;
import org.springframework.format.number.PercentStyleFormatter;
import org.springframework.format.support.FormattingConversionService;

import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Locale;

public class FormatterTest {

    public static void main(String[] args) {
        test8();
    }

    public void test1() {
        // DateTimeFormatterFactory dateTimeFormatterFactory = new DateTimeFormatterFactory();
        // dateTimeFormatterFactory.setPattern("yyyy-MM-dd HH:mm:ss");

        // 执行格式化动作
        System.out.println(new DateTimeFormatterFactory("yyyy-MM-dd HH:mm:ss").createDateTimeFormatter().format(LocalDateTime.now()));
        System.out.println(new DateTimeFormatterFactory("yyyy-MM-dd").createDateTimeFormatter().format(LocalDate.now()));
        System.out.println(new DateTimeFormatterFactory("HH:mm:ss").createDateTimeFormatter().format(LocalTime.now()));
        System.out.println(new DateTimeFormatterFactory("yyyy-MM-dd HH:mm:ss").createDateTimeFormatter().format(ZonedDateTime.now()));
    }

    public void test2() throws ParseException {
        NumberStyleFormatter formatter = new NumberStyleFormatter();

        double myNum = 1220.0455;
        System.out.println(formatter.print(myNum, Locale.getDefault()));

        formatter.setPattern("#.##");
        System.out.println(formatter.print(myNum, Locale.getDefault()));

        // 转换
        // Number parsedResult = formatter.parse("1,220.045", Locale.getDefault()); // java.text.ParseException: 1,220.045
        Number parsedResult = formatter.parse("1220.045", Locale.getDefault());
        System.out.println(parsedResult.getClass() + "-->" + parsedResult);
    }

    public void test3() throws ParseException {
        PercentStyleFormatter formatter = new PercentStyleFormatter();

        double myNum = 1220.0455;
        System.out.println(formatter.print(myNum, Locale.getDefault()));

        // 转换
        // Number parsedResult = formatter.parse("1,220.045", Locale.getDefault()); // java.text.ParseException: 1,220.045
        Number parsedResult = formatter.parse("122,005%", Locale.getDefault());
        System.out.println(parsedResult.getClass() + "-->" + parsedResult);
    }

    public void test4() throws ParseException {
        CurrencyStyleFormatter formatter = new CurrencyStyleFormatter();

        double myNum = 1220.0455;
        System.out.println(formatter.print(myNum, Locale.getDefault()));

        System.out.println("--------------定制化--------------");
        // 指定货币种类（如果你知道的话）
        // formatter.setCurrency(Currency.getInstance(Locale.getDefault()));
        // 指定所需的分数位数。默认是2
        formatter.setFractionDigits(1);
        // 舍入模式。默认是RoundingMode#UNNECESSARY
        formatter.setRoundingMode(RoundingMode.CEILING);
        // 格式化数字的模版
        formatter.setPattern("#.#¤¤");

        System.out.println(formatter.print(myNum, Locale.getDefault()));

        // 转换
        // Number parsedResult = formatter.parse("￥1220.05", Locale.getDefault());
        Number parsedResult = formatter.parse("1220.1CNY", Locale.getDefault());
        System.out.println(parsedResult.getClass() + "-->" + parsedResult);
    }

    public static void test5() {
        FormattingConversionService formattingConversionService = new FormattingConversionService();
        FormatterRegistry formatterRegistry = formattingConversionService;
        // 说明：这里不使用DefaultConversionService是为了避免默认注册的那些转换器对结果的“干扰”，不方便看效果
        // ConversionService conversionService = new DefaultConversionService();
        ConversionService conversionService = formattingConversionService;

        // 注册格式化器
        formatterRegistry.addPrinter(new IntegerPrinter());

        // 最终均使用ConversionService统一提供服务转换
        System.out.println(conversionService.canConvert(Integer.class, String.class));
        System.out.println(conversionService.canConvert(Person.class, String.class));

        System.out.println(conversionService.convert(1, String.class));
        // 报错：No converter found capable of converting from type [cn.yourbatman.bean.Person] to type [java.lang.String]
        // System.out.println(conversionService.convert(new Person(1, "YourBatman"), String.class));
    }

    public static void test6() {
        FormattingConversionService formattingConversionService = new FormattingConversionService();
        FormatterRegistry formatterRegistry = formattingConversionService;
        // 说明：这里不使用DefaultConversionService是为了避免默认注册的那些转换器对结果的“干扰”，不方便看效果
        // ConversionService conversionService = new DefaultConversionService();
        ConversionService conversionService = formattingConversionService;

        // 注册格式化器
        formatterRegistry.addFormatterForFieldType(Person.class, new IntegerPrinter(), null);
        // 强调：此处绝不能使用lambda表达式代替，否则泛型类型丢失，结果将出错
        formatterRegistry.addConverter(new Converter<Person, Integer>() {
            @Override
            public Integer convert(Person source) {
                return source.getId();
            }
        });

        // 最终均使用ConversionService统一提供服务转换
        System.out.println(conversionService.canConvert(Person.class, String.class));
        System.out.println(conversionService.convert(new Person(1, "YourBatman"), String.class));
    }

    public void test7() {
        FormattingConversionService formattingConversionService = new FormattingConversionService();
        FormatterRegistry formatterRegistry = formattingConversionService;
        ConversionService conversionService = formattingConversionService;

        // 注册格式化器
        formatterRegistry.addParser(new IntegerParser());

        System.out.println(conversionService.canConvert(String.class, Integer.class));
        System.out.println(conversionService.convert("1", Integer.class));
    }

    public static void test8() {
        FormattingConversionService formattingConversionService = new FormattingConversionService();
        FormatterRegistry formatterRegistry = formattingConversionService;
        ConversionService conversionService = formattingConversionService;

        // 注册格式化器
        formatterRegistry.addFormatterForFieldType(Person.class, null, new IntegerParser());
        formatterRegistry.addConverter(new Converter<Integer, Person>() {
            @Override
            public Person convert(Integer source) {
                return new Person(source, "YourBatman");
            }
        });

        System.out.println(conversionService.canConvert(String.class, Person.class));
        System.out.println(conversionService.convert("1", Person.class));
    }



}

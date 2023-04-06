package convert.formatter.annotationFormatterFactory;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;
import org.springframework.format.number.NumberFormatAnnotationFormatterFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AnnotationFormatterFactoryTest {

    public static void main(String[] args) throws Exception {
        test7();
    }

    public static void test1() throws Exception {
        AnnotationFormatterFactory annotationFormatterFactory = new DateTimeFormatAnnotationFormatterFactory();

        // 找到该field
        Field field = Person.class.getDeclaredField("birthday");
        DateTimeFormat annotation = field.getAnnotation(DateTimeFormat.class);
        Class<?> type = field.getType();

        // 输出：
        System.out.println("输出：Date -> String====================");
        Printer printer = annotationFormatterFactory.getPrinter(annotation, type);
        Person person = new Person(new Date());
        System.out.println(printer.print(person.getBirthday(), Locale.US));

        // 输入：
        System.out.println("输入：String -> Date====================");
        Parser parser = annotationFormatterFactory.getParser(annotation, type);
        Object output = parser.parse("2021-02-06 19:00:00", Locale.US);
        person = new Person((Date) output);
        System.out.println(person);
    }

    public static void test2() throws NoSuchMethodException, ParseException {
        AnnotationFormatterFactory annotationFormatterFactory = new MyDateTimeFormatAnnotationFormatterFactory();

        // 拿到方法入参
        Method method = AnnotationFormatterFactoryTest.class.getDeclaredMethod("method", Calendar.class);
        Parameter parameter = method.getParameters()[0];
        DateTimeFormat annotation = parameter.getAnnotation(DateTimeFormat.class);
        Class<?> type = parameter.getType();

        // 输入：
        System.out.println("输入：String -> Calendar====================");
        Parser parser = annotationFormatterFactory.getParser(annotation, type);
        Object output = parser.parse("2021-02-06 19:00:00", Locale.US);
        // 给该方法传入“转换好的”参数，表示输入
        method((Calendar) output);
    }

    public static void method(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Calendar calendar) {
        System.out.println(calendar);
        System.out.println(calendar.getTime());
    }

    public static void test4() throws NoSuchFieldException, ParseException {
        AnnotationFormatterFactory annotationFormatterFactory = new Jsr310DateTimeFormatAnnotationFormatterFactory();

        // 找到该field
        Field field = Father.class.getDeclaredField("birthday");
        DateTimeFormat annotation = field.getAnnotation(DateTimeFormat.class);
        Class<?> type = field.getType();

        // 输出：
        System.out.println("输出：LocalDate -> String====================");
        Printer printer = annotationFormatterFactory.getPrinter(annotation, type);
        Father father = new Father(LocalDate.now());
        System.out.println(printer.print(father.getBirthday(), Locale.US));

        // 输入：
        System.out.println("输入：String -> Date====================");
        Parser parser = annotationFormatterFactory.getParser(annotation, type);
        Object output = parser.parse("2021-02-07", Locale.US);
        father = new Father((LocalDate) output);
        System.out.println(father);
    }

    public void test5() throws ParseException, NoSuchMethodException {
        AnnotationFormatterFactory annotationFormatterFactory = new Jsr310DateTimeFormatAnnotationFormatterFactory();

        // 拿到方法入参
        Method method = this.getClass().getDeclaredMethod("methodJSR310", LocalDate.class);
        Parameter parameter = method.getParameters()[0];
        DateTimeFormat annotation = parameter.getAnnotation(DateTimeFormat.class);
        Class<?> type = parameter.getType();


        // 输入：
        System.out.println("输入：String -> LocalDate====================");
        Parser parser = annotationFormatterFactory.getParser(annotation, type);
        Object output = parser.parse("2021-02-06", Locale.US);
        // 给该方法传入“转换好的”参数，表示输入
        methodJSR310((LocalDate) output);
    }

    public void methodJSR310(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        System.out.println(localDate);
    }

    public void test6() throws NoSuchMethodException {
        AnnotationFormatterFactory annotationFormatterFactory = new Jsr310DateTimeFormatAnnotationFormatterFactory();

        // 拿到方法返回值类型
        Method method = this.getClass().getDeclaredMethod("method1JSR310");
        DateTimeFormat annotation = method.getAnnotation(DateTimeFormat.class);
        Class<?> type = method.getReturnType();


        // 输出：
        System.out.println("输出：LocalDate -> 时间格式的String====================");
        Printer printer = annotationFormatterFactory.getPrinter(annotation, type);

        LocalDate returnValue = method1JSR310();
        System.out.println(printer.print(returnValue, Locale.US));
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate method1JSR310() {
        return LocalDate.now();
    }

    public static void test7() throws NoSuchMethodException, ParseException {
        AnnotationFormatterFactory annotationFormatterFactory = new NumberFormatAnnotationFormatterFactory();

        // 获取待处理的目标类型（方法参数、字段属性、方法返回值等等）
        Method method1 = AnnotationFormatterFactoryTest.class.getMethod("method2", double.class);
        Parameter parameter = method1.getParameters()[0];
        NumberFormat annotation = parameter.getAnnotation(NumberFormat.class);
        Class<?> fieldType = parameter.getType();

        // 1、根据注解和field类型生成一个解析器，完成String -> LocalDateTime
        Parser parser = annotationFormatterFactory.getParser(annotation, fieldType);
        // 2、模拟转换动作，并输出结果
        Object result = parser.parse("11%", Locale.US);
        System.out.println(result.getClass());
        System.out.println(result);

    }

    public void method2(@NumberFormat(style = NumberFormat.Style.PERCENT) double d) {
    }
}

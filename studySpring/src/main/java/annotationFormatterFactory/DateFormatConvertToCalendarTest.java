package annotationFormatterFactory;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Locale;

public class DateFormatConvertToCalendarTest {

    public static void main(String[] args) throws ParseException, NoSuchMethodException {

//        /**
//         * parser.parse()转换后为date类型，强转Calendar会报错
//         */
//        AnnotationFormatterFactory annotationFormatterFactory = new DateTimeFormatAnnotationFormatterFactory();

        /**
         * 自定义MyDateTimeFormatAnnotationFormatterFactory 当filedType为Calendar类型会处理
         */
        AnnotationFormatterFactory annotationFormatterFactory = new MyDateTimeFormatAnnotationFormatterFactory();

        // 拿到方法入参
        Method method = DateFormatConvertToCalendarTest.class.getDeclaredMethod("method", Calendar.class);
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
}


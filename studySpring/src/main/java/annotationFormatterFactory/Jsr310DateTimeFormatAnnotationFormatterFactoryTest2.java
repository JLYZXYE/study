package annotationFormatterFactory;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

public class Jsr310DateTimeFormatAnnotationFormatterFactoryTest2 {
    public static void main(String[] args) throws ParseException, NoSuchFieldException, NoSuchMethodException {
        AnnotationFormatterFactory annotationFormatterFactory = new Jsr310DateTimeFormatAnnotationFormatterFactory();

        // 拿到方法入参
        Method method = Jsr310DateTimeFormatAnnotationFormatterFactoryTest2.class.getDeclaredMethod("methodJSR310", LocalDate.class);
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

    public static void methodJSR310(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        System.out.println(localDate);
    }
}

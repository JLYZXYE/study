package annotationFormatterFactory;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

public class Jsr310DateTimeFormatAnnotationFormatterFactoryTest {
    public static void main(String[] args) throws ParseException, NoSuchFieldException {
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
}

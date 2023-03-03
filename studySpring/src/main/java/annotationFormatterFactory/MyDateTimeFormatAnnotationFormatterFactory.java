package annotationFormatterFactory;

import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class MyDateTimeFormatAnnotationFormatterFactory extends DateTimeFormatAnnotationFormatterFactory {

    @Override
    public Parser<?> getParser(DateTimeFormat annotation, Class<?> fieldType) {
        if (fieldType.isAssignableFrom(Calendar.class)) {
            return new Parser<Calendar>() {
                @Override
                public Calendar parse(String text, Locale locale) throws ParseException {
                    // 先翻译为Date
                    Formatter<Date> formatter = getFormatter(annotation, fieldType);
                    Date date = formatter.parse(text, locale);

                    // 再翻译为Calendar
                    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                    calendar.setTime(date);
                    return calendar;
                }

            };
        }
        return super.getParser(annotation, fieldType);
    }
}
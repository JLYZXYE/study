package convert.formatter;

import org.springframework.format.Printer;

import java.util.Locale;

public class IntegerPrinter implements Printer<Integer> {

    @Override
    public String print(Integer object, Locale locale) {
        object += 10;
        return object.toString();
    }
}
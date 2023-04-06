package convert.formatter;

import org.springframework.format.Parser;
import org.springframework.util.NumberUtils;

import java.text.ParseException;
import java.util.Locale;

class IntegerParser implements Parser<Integer> {

    @Override
    public Integer parse(String text, Locale locale) throws ParseException {
        return NumberUtils.parseNumber(text, Integer.class);
    }
}
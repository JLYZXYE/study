package propertyEditorRegistrar;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class LocalDateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        LocalDate value = LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        setValue(value);
    }
}

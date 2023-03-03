package annotationFormatterFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
class Father {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
}
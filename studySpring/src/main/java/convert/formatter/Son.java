package convert.formatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
class Son {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthday;

}
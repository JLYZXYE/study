package propertyEditorRegistrar;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Data
public class Person {

    private String name;

    private String gender;

    private Integer age;

    @Value("2022-12-01")
    private LocalDate birthDay;

}

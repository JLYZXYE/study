package domain;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Person {

    @NotNull
    public String name;
    @NotNull
    @Min(0)
    public Integer age;
}
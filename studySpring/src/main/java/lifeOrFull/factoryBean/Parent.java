package lifeOrFull.factoryBean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Parent {

    public Parent(Son son) {
        this.son = son;
    }

    private Son son;

    private String name;
    private Integer age;
}
package org.springframework.core.convert.support.ObjectToObject;

import lombok.Data;

// tartgetClass
@Data
public class Person {
    private Long id;
    private String name;



    /**
     * 方法名称可以是：valueOf、of、from
     */
    public static Person valueOf(Customer customer) {
        Person person = new Person();
        person.setId(customer.getId());
        person.setName("YourBatman-".concat(customer.getAddress()));
        return person;
    }


//    public Person(Customer customer) {
//        this.setId(customer.getId());
//        this.setName("YourBatman-".concat(customer.getAddress()));
//    }
}

package org.springframework.core.convert.support.ObjectToObject;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String address;

//    public Person toPerson() {
//        Person person = new Person();
//        person.setId(getId());
//        person.setName("YourBatman-".concat(getAddress()));
//        return person;
//    }


}


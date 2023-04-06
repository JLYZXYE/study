package org.springframework.core.convert.support.IdToEntity;

import lombok.Data;

@Data
public class Person {

    private Long id;
    private String name;

    /**
     * 根据ID定位一个Person实例
     */
    public static Person findPerson(Long id) {
        // 一般根据id从数据库查，本处通过new来模拟
        Person person = new Person();
        person.setId(id);
        person.setName("YourBatman-byFindPerson");
        return person;
    }

}
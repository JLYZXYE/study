package service;

import domain.Person;
import interfaces.PersonInterface;
import utils.ValidatorUtil;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.Set;

public class PersonService implements PersonInterface {

    @Override
    public void save(Person person) throws NoSuchMethodException {
        Method currMethod = this.getClass().getMethod("save", Person.class);
        Set<ConstraintViolation<PersonService>> validResult = ValidatorUtil.obtainExecutableValidator().validateParameters(this, currMethod, new Object[]{person});
        if (!validResult.isEmpty()) {
            // ... 输出错误详情validResult
            validResult.stream().map(v -> v.getPropertyPath() + " " + v.getMessage() + ": " + v.getInvalidValue()).forEach(System.out::println);
            throw new IllegalArgumentException("参数错误");
        }
    }

}
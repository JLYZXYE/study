package utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.util.Set;

public abstract class ValidatorUtil {

    public static ValidatorFactory obtainValidatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    public static Validator obtainValidator() {
        return obtainValidatorFactory().getValidator();
    }

    public static ExecutableValidator obtainExecutableValidator() {
        return obtainValidator().forExecutables();
    }

    public static <T> void printViolations(Set<ConstraintViolation<T>> violations) {
        violations.stream().map(v -> v.getPropertyPath()  + v.getMessage() + "，但你的值是： " + v.getInvalidValue()).forEach(System.out::println);
    }

}
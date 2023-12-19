package annotions;

import constraintValidator.ValidStudentCountConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = {ValidStudentCountConstraintValidator.class})
public @interface ValidStudentCount {
    String message() default "学生人数超过最大限额";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
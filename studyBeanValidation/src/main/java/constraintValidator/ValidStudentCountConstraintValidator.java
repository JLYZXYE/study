package constraintValidator;

import annotions.ValidStudentCount;
import domain.RoomNum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStudentCountConstraintValidator implements ConstraintValidator<ValidStudentCount, RoomNum> {

    @Override
    public void initialize(ValidStudentCount constraintAnnotation) {
    }

    @Override
    public boolean isValid(RoomNum room, ConstraintValidatorContext context) {
        if (room == null) {
            return true;
        }
        boolean isValid = false;
        if (room.getStudentNames().size() <= room.getMaxStuNum()) {
            isValid = true;
        }

        // 自定义提示语（当然你也可以不自定义，那就使用注解里的message字段的值）
        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("校验失败xxx")
                    .addPropertyNode("studentNames")
                    .addConstraintViolation();
        }
        return isValid;
    }
}

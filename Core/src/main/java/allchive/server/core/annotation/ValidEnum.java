package allchive.server.core.annotation;

import allchive.server.core.validator.EnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
public @interface ValidEnum {
    String message() default "올바른 값을 입력해주세요";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<? extends java.lang.Enum<?>> target();
}

package git.MatheusOliveira04.models.enums.validations.annotations;

import git.MatheusOliveira04.models.enums.validations.ValueOfEnumValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "must be any 'value' of enum {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

package git.MatheusOliveira04.models.enums.validations;

import git.MatheusOliveira04.models.enums.validations.annotations.ValueOfEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream
                .of(annotation.enumClass().getEnumConstants())
                .map(enumConstant -> {
                   try {
                       return (String) enumConstant.getClass().getMethod("getValue").invoke(enumConstant);
                   } catch (Exception e) {
                       throw new RuntimeException("Error accessing getValue method", e);
                   }
                })
                .toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null || "".equals(value.toString().trim())) {
            return true;
        }
        if (!acceptedValues.contains(value.toString())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Only the values are accepted: " + acceptedValues)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}

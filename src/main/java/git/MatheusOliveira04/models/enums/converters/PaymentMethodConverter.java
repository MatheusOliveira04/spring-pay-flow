package git.MatheusOliveira04.models.enums.converters;

import git.MatheusOliveira04.models.enums.PaymentMethod;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {

    @Override
    public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
        return Optional.ofNullable(paymentMethod)
                .map(PaymentMethod::getValue)
                .orElse(null);
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String value) {
        return Optional.ofNullable(value)
                .map(valueS -> {
                    return Stream.of(PaymentMethod.values())
                            .filter(paymentMethod -> paymentMethod.getValue().equals(value))
                            .findFirst()
                            .orElseThrow(IllegalAccessError::new);
                })
                .orElse(null);
    }
}

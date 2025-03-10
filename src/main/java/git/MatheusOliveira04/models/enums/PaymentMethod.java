package git.MatheusOliveira04.models.enums;

import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.stream.Stream;

@Getter
public enum PaymentMethod {
    CASH("Cash"),
    PIX("Pix");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    public static PaymentMethod parse(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        return Stream.of(PaymentMethod.values())
                .filter(paymentMethod -> paymentMethod.getValue().equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}

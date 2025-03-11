package git.MatheusOliveira04.models.dtos.request;

import git.MatheusOliveira04.models.enums.PaymentMethod;
import git.MatheusOliveira04.models.enums.validations.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull
    @ValueOfEnum(enumClass = PaymentMethod.class)
    String paymentMethod;
    @NotNull @Positive
    BigDecimal amountReceived;
}

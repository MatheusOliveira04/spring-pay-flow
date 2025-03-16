package git.MatheusOliveira04.models.dtos.request;

import git.MatheusOliveira04.models.enums.PaymentMethod;
import git.MatheusOliveira04.models.enums.validations.annotations.ValueOfEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotNull
    @NotEmpty
    @ValueOfEnum(enumClass = PaymentMethod.class)
    String paymentMethod;
    @NotNull
    @Positive
    BigDecimal amountReceived;
}

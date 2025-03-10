package git.MatheusOliveira04.models.dtos.request;

import git.MatheusOliveira04.models.enums.PaymentMethod;
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
    PaymentMethod paymentMethod;
    @NotNull @Positive
    BigDecimal amountReceived;
}

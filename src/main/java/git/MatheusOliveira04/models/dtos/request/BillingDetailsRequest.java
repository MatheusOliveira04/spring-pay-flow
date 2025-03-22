package git.MatheusOliveira04.models.dtos.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetailsRequest {
    @NotNull @DecimalMin(value = "0.01") private BigDecimal totalAmountToPay;
}

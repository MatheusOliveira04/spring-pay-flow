package git.MatheusOliveira04.models.dtos.reponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {
    private UUID id;
    private String paymentMethod;
    private BigDecimal amountReceived;
    private UUID saleId;
}

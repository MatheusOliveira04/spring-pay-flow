package git.MatheusOliveira04.models.dtos.reponse;

import git.MatheusOliveira04.models.BillingDetails;
import git.MatheusOliveira04.models.enums.StatusSale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponse {
    private UUID id;
    private String description;
    private String code;
    private StatusSale status;
    private BillingDetails billingDetails;
    private LocalDate dateToPay;
    private LocalDate datePayed;
    private List<PaymentResponse> payments;
}

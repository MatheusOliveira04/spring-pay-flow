package git.MatheusOliveira04.models.dtos.request;

import git.MatheusOliveira04.models.BillingDetails;
import git.MatheusOliveira04.models.enums.StatusSale;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {
    private String description;
    private StatusSale status;
    private BillingDetails billingDetails;
    private LocalDate dateToPay;
    private LocalDate datePayed;
    private List<PaymentRequest> payments;
}

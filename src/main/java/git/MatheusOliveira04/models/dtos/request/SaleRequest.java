package git.MatheusOliveira04.models.dtos.request;

import git.MatheusOliveira04.models.BillingDetails;
import git.MatheusOliveira04.models.enums.StatusSale;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequest {
    @Length(max = 100)
    private String description;
    @NotNull
    private StatusSale status;
    @NotNull @Valid
    private BillingDetails billingDetails;
    @NotNull @FutureOrPresent
    private LocalDate dateToPay;
    @PastOrPresent
    private LocalDate datePayed;
    @NotEmpty @Valid
    private List<@NotNull PaymentRequest> payments;
}

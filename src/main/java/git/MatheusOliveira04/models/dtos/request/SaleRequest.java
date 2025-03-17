package git.MatheusOliveira04.models.dtos.request;

import git.MatheusOliveira04.models.enums.StatusSale;
import git.MatheusOliveira04.models.enums.validations.annotations.ValueOfEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    @ValueOfEnum(enumClass = StatusSale.class)
    private String status;
    @NotNull @Valid
    private BillingDetailsRequest billingDetails;
    @NotNull @FutureOrPresent
    private LocalDate dateToPay;
    @PastOrPresent
    private LocalDate datePayed;
    @Valid
    private List<@NotNull PaymentRequest> payments;
}

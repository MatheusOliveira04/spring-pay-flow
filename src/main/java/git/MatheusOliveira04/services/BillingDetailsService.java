package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.BillingDetails;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public interface BillingDetailsService {

    void calculateCashBask(@NotNull BillingDetails billingDetails);
}

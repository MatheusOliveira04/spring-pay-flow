package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.BillingDetails;
import jakarta.validation.constraints.NotNull;

public interface BillingDetailsService {

    void calculateCashBack(@NotNull BillingDetails billingDetails);
}

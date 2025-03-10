package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
public interface PaymentService {

    void pay(@NotNull @Valid PaymentMethod paymentMethod, @NotNull @Positive BigDecimal amount);
}

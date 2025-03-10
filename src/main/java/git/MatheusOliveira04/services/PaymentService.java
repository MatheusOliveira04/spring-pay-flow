package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.enums.PaymentMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

public interface PaymentService {

    void pay(PaymentMethod paymentMethod, BigDecimal amount);
}

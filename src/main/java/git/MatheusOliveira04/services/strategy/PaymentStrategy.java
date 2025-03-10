package git.MatheusOliveira04.services.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {

    void pay(BigDecimal amount);

}

package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.enums.PaymentMethod;
import git.MatheusOliveira04.services.strategy.PaymentStrategy;
import git.MatheusOliveira04.services.strategy.impls.CashPaymentStrategy;
import git.MatheusOliveira04.services.strategy.impls.PixPaymentStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class PaymentServiceImpl {

    private final Map<PaymentMethod, PaymentStrategy> mapPaymentMethod = Map.of(
            PaymentMethod.PIX, new PixPaymentStrategy(),
            PaymentMethod.CASH, new CashPaymentStrategy()
    );

    public void pay(PaymentMethod paymentMethod, BigDecimal amount) {
        mapPaymentMethod.get(paymentMethod).pay(amount);
    }
}

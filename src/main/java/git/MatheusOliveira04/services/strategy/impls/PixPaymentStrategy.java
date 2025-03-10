package git.MatheusOliveira04.services.strategy.impls;

import git.MatheusOliveira04.services.strategy.PaymentStrategy;

import java.math.BigDecimal;

public class PixPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(BigDecimal amount) {
        System.out.println("Pay by pix");
    }
}

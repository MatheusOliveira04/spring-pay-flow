package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.BillingDetails;
import git.MatheusOliveira04.services.BillingDetailsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BillingDetailsServiceImpl implements BillingDetailsService {
    @Override
    public void calculateCashBask(BillingDetails billingDetails) {
        BigDecimal totalAmountToPay = BigDecimal.valueOf(billingDetails.getTotalAmountToPay().doubleValue());
        BigDecimal totalPaid = BigDecimal.valueOf(billingDetails.getTotalPaid().doubleValue());
        billingDetails.setCashBack(totalAmountToPay.subtract(totalPaid));
    }
}

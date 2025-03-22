package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.BillingDetails;
import git.MatheusOliveira04.services.BillingDetailsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BillingDetailsServiceImpl implements BillingDetailsService {
    @Override
    public void calculateCashBask(BillingDetails billingDetails) {
        BigDecimal totalAmountToPay = billingDetails.getTotalAmountToPay();
        BigDecimal totalPaid = billingDetails.getTotalPaid();

        if (totalPaid.compareTo(totalAmountToPay) > 0) {
            billingDetails.setCashBack(totalPaid.subtract(totalAmountToPay));
        }
    }
}

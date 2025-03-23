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

        if (isTotalPaidGreaterThanAmountToPay(totalPaid, totalAmountToPay)) {
            billingDetails.setCashBack(totalPaid.subtract(totalAmountToPay));
        } else {
            billingDetails.setCashBack(BigDecimal.ZERO);
        }
    }

    private Boolean isTotalPaidGreaterThanAmountToPay(BigDecimal totalPaid, BigDecimal totalAmountToPay) {
        return totalPaid.compareTo(totalAmountToPay) > 0;
    }
}

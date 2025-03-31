package git.MatheusOliveira04.services;

import git.MatheusOliveira04.models.BillingDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Transactional
public class BillingDetailsServiceTest extends BaseTest {

    @Autowired
    BillingDetailsService billingDetailsService;

    @Test
    @DisplayName("Calculate of cash back with value total to pay greater than total paid")
    void calculateCashBackTotalToPayGreaterThanTotalPaid() {
        BillingDetails billingDetails = BillingDetails.builder()
                .id(null)
                .sale(null)
                .totalAmountToPay(new BigDecimal(100))
                .totalPaid(new BigDecimal(50))
                .cashBack(new BigDecimal(0))
                .build();

        billingDetailsService.calculateCashBack(billingDetails);
        assertEquals(BigDecimal.ZERO, billingDetails.getCashBack());
    }

    @Test
    @DisplayName("Calculate of cash back with value total paid greater than total to pay")
    void calculateCashBackValueTotalGreaterThanTotalToPay() {
        var billingDetails = BillingDetails.builder()
                .id(null)
                .sale(null)
                .totalAmountToPay(new BigDecimal(100))
                .totalPaid(new BigDecimal(150))
                .cashBack(new BigDecimal(0))
                .build();

        billingDetailsService.calculateCashBack(billingDetails);
        assertEquals(new BigDecimal(50), billingDetails.getCashBack());
    }
}

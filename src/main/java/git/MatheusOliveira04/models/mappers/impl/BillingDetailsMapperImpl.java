package git.MatheusOliveira04.models.mappers.impl;

import git.MatheusOliveira04.models.BillingDetails;
import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.BillingDetailsRequest;
import git.MatheusOliveira04.models.mappers.BillingDetailsMapper;
import org.springframework.stereotype.Component;

@Component
public class BillingDetailsMapperImpl implements BillingDetailsMapper {

    @Override
    public BillingDetails toBillingDetails(BillingDetailsRequest billingDetailsRequest, Sale sale) {
        return BillingDetails.builder()
                .totalAmountToPay(billingDetailsRequest.getTotalAmountToPay())
                .totalPaid(billingDetailsRequest.getTotalPaid())
                .cashBack(billingDetailsRequest.getCashBack())
                .sale(sale)
                .build();
    }
}

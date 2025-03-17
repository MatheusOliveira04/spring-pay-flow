package git.MatheusOliveira04.models.mappers.impl;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;
import git.MatheusOliveira04.models.enums.StatusSale;
import git.MatheusOliveira04.models.mappers.BillingDetailsMapper;
import git.MatheusOliveira04.models.mappers.PaymentMapper;
import git.MatheusOliveira04.models.mappers.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleMapperImpl implements SaleMapper {

    @Autowired
    private BillingDetailsMapper billingDetailsMapper;

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Sale toSale(SaleRequest saleRequest) {

        Sale sale = Sale.builder()
                .description(saleRequest.getDescription())
                .status(StatusSale.parse(saleRequest.getStatus()))
                .dateToPay(saleRequest.getDateToPay())
                .datePayed(saleRequest.getDatePayed())
                .build();

        sale.setBillingDetails(billingDetailsMapper.toBillingDetails(saleRequest.getBillingDetails(), sale));

        sale.setPayments(saleRequest.getPayments()
                .stream().map(paymentRequest -> paymentMapper.toPayment(paymentRequest, sale))
                .toList());

        return sale;
    }
}

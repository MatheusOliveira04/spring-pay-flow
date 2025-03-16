package git.MatheusOliveira04.models.mappers.impl;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;
import git.MatheusOliveira04.models.mappers.PaymentMapper;
import git.MatheusOliveira04.models.mappers.SaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SaleMapperImpl implements SaleMapper {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public Sale toSale(SaleRequest saleRequest) {

        Sale sale = Sale.builder()
                .description(saleRequest.getDescription())
                .status(saleRequest.getStatus())
                .billingDetails(saleRequest.getBillingDetails())
                .dateToPay(saleRequest.getDateToPay())
                .datePayed(saleRequest.getDatePayed())
                .build();

        sale.setPayments(saleRequest.getPayments()
                .stream().map(paymentRequest -> paymentMapper.toPayment(paymentRequest, sale))
                .toList());

        return sale;
    }
}

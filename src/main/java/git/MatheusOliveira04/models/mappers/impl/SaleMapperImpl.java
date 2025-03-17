package git.MatheusOliveira04.models.mappers.impl;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;
import git.MatheusOliveira04.models.enums.StatusSale;
import git.MatheusOliveira04.models.mappers.BillingDetailsMapper;
import git.MatheusOliveira04.models.mappers.PaymentMapper;
import git.MatheusOliveira04.models.mappers.SaleMapper;
import git.MatheusOliveira04.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

        convertBillingDetailsRequestForBillingDetails(saleRequest, sale);
        convertListPaymentRequestForListPayment(saleRequest, sale);

        return sale;
    }

    private void convertBillingDetailsRequestForBillingDetails(SaleRequest saleRequest, Sale sale) {
        if (Objects.nonNull(saleRequest.getBillingDetails())) {
            sale.setBillingDetails(billingDetailsMapper.toBillingDetails(saleRequest.getBillingDetails(), sale));
        }
    }

    private void convertListPaymentRequestForListPayment(SaleRequest saleRequest, Sale sale) {
        if (!ListUtils.isNullOrEmpty(saleRequest.getPayments())) {
            sale.setPayments(paymentMapper.toPayment(saleRequest.getPayments(), sale));
        }
    }
}

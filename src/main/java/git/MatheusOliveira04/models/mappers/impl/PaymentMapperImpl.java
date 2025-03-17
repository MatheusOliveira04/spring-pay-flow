package git.MatheusOliveira04.models.mappers.impl;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.PaymentRequest;
import git.MatheusOliveira04.models.enums.PaymentMethod;
import git.MatheusOliveira04.models.mappers.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public Payment toPayment(PaymentRequest paymentRequest, Sale sale) {
        return Payment.builder()
            .paymentMethod(PaymentMethod.parse(paymentRequest.getPaymentMethod()))
            .amountReceived(paymentRequest.getAmountReceived())
            .sale(sale)
            .build();
    }

    @Override
    public List<Payment> toPayment(List<PaymentRequest> paymentRequests, Sale sale) {
        return paymentRequests.stream()
                .map(paymentRequest -> Payment.builder()
                        .paymentMethod(PaymentMethod.parse(paymentRequest.getPaymentMethod()))
                        .amountReceived(paymentRequest.getAmountReceived())
                        .sale(sale)
                        .build())
                .toList();
    }
}

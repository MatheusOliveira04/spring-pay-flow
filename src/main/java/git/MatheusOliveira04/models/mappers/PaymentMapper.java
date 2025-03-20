package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.reponse.PaymentResponse;
import git.MatheusOliveira04.models.dtos.request.PaymentRequest;

import java.util.List;

public interface PaymentMapper {

    Payment toPayment(PaymentRequest paymentRequest, Sale sale);

    List<Payment> toPayment(List<PaymentRequest> paymentRequests, Sale sale);

    PaymentResponse toPaymentResponse(Payment payment);

    List<PaymentResponse> toPaymentResponse(List<Payment> payments);
}

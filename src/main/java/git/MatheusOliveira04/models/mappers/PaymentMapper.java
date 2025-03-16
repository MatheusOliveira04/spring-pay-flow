package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.PaymentRequest;

public interface PaymentMapper {

    Payment toPayment(PaymentRequest paymentRequest, Sale sale);
}

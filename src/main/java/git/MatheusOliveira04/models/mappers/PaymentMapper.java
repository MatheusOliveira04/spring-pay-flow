package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.models.dtos.reponse.PaymentResponse;
import git.MatheusOliveira04.models.dtos.request.PaymentRequest;
import git.MatheusOliveira04.models.enums.PaymentMethod;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentMapper {


    @Mapping(target = "id", ignore = true)
    Payment toPayment(PaymentRequest paymentRequest);

    PaymentResponse toPaymentResponse(Payment payment);

    default PaymentMethod mapValueToPaymentMethod(String valuePaymentMethod) {
        if (valuePaymentMethod == null) {
            return null;
        }
        return PaymentMethod.parse(valuePaymentMethod);
    }

    default String mapPaymentMethodToValue(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }
        return paymentMethod.getValue();
    }
}

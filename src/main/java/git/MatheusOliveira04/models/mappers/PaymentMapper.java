package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.models.dtos.reponse.PaymentResponse;
import git.MatheusOliveira04.models.dtos.request.PaymentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    Payment toPayment(PaymentRequest paymentRequest);

    PaymentResponse toPaymentResponse(Payment payment);
}

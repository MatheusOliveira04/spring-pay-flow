package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.BillingDetails;
import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.BillingDetailsRequest;

public interface BillingDetailsMapper {

    BillingDetails toBillingDetails(BillingDetailsRequest billingDetailsRequest, Sale sale);

}

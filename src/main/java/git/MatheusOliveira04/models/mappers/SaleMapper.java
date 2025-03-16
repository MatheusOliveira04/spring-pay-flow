package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.request.PaymentRequest;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;

public interface SaleMapper {

    Sale toSale(SaleRequest saleRequest);

}

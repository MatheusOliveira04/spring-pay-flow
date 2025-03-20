package git.MatheusOliveira04.models.mappers;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.dtos.reponse.SaleResponse;
import git.MatheusOliveira04.models.dtos.request.SaleRequest;

import java.util.List;

public interface SaleMapper {

    Sale toSale(SaleRequest saleRequest);

    SaleResponse toSaleResponse(Sale sale);

    List<SaleResponse> toSaleResponse(List<Sale> sales);
}

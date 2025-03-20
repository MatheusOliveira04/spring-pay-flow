package git.MatheusOliveira04.models.dtos.reponse;

import git.MatheusOliveira04.models.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalePageResponse {
    private List<Sale> sales;
    private Long totalItems;
    private Integer totalPages;
}

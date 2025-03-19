package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.Payment;
import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.enums.StatusSale;
import git.MatheusOliveira04.repositories.SaleRepository;
import git.MatheusOliveira04.services.SaleService;
import git.MatheusOliveira04.services.exception.ObjectNotFoundException;
import git.MatheusOliveira04.services.strategy.StatusSaleStrategy;
import git.MatheusOliveira04.services.strategy.impl.DueStatusSaleStrategyImpl;
import git.MatheusOliveira04.services.strategy.impl.NotReceivedStatusSaleStrategyImpl;
import git.MatheusOliveira04.services.strategy.impl.ReceivedStatusSaleStrategyImpl;
import git.MatheusOliveira04.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    private final Map<StatusSale, StatusSaleStrategy> mapStatusSaleStrategy = Map.of(
            StatusSale.DUE, new DueStatusSaleStrategyImpl(),
            StatusSale.RECEIVED, new ReceivedStatusSaleStrategyImpl(),
            StatusSale.NOT_RECEIVED, new NotReceivedStatusSaleStrategyImpl()
    );

    @Override
    public List<Sale> findAll() {
        List<Sale> salesFound = saleRepository.findAll();
        if (salesFound.isEmpty()) {
            throw new ObjectNotFoundException("No sale found.");
        }
        return salesFound;
    }

    @Override
    public Sale findById(UUID id) {
        return saleRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Sale not found with id: " + id));
    }

    @Override
    public Sale insert(Sale sale) {
        validateStatus(sale);
        receivedValueTotalPayed(sale);
        return saleRepository.save(sale);
    }

    @Override
    public Sale update(Sale sale) {
        findById(sale.getId());
        validateStatus(sale);
        receivedValueTotalPayed(sale);
        return saleRepository.save(sale);
    }

    @Override
    public void delete(UUID id) {

    }

    private void receivedValueTotalPayed(Sale sale) {
        sale.getBillingDetails().setTotalPaid(sumAmountReceivedOfAllPayments(sale));
    }

    private BigDecimal sumAmountReceivedOfAllPayments(Sale sale) {
        if (ListUtils.isNullOrEmpty(sale.getPayments())) {
            return BigDecimal.ZERO;
        }

        return sale.getPayments()
                .stream()
                .map(Payment::getAmountReceived)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validateStatus(Sale sale) {
        mapStatusSaleStrategy.get(sale.getStatus()).validateStatusSale(sale);
    }
}

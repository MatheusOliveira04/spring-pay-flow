package git.MatheusOliveira04.services.impls;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.models.enums.StatusSale;
import git.MatheusOliveira04.repositories.SaleRepository;
import git.MatheusOliveira04.services.SaleService;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import git.MatheusOliveira04.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<Sale> findAll() {
        return List.of();
    }

    @Override
    public Sale findById(UUID id) {
        return null;
    }

    @Override
    public Sale insert(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale update(Sale sale) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    private void validateStatus(Sale sale) {
    }

    private void statusReceived(Sale sale) {
        if (sale.getStatus() == StatusSale.RECEIVED) {

            if (ListUtils.isNullOrEmpty(sale.getPayments())) {
                throw new IntegrityViolationException("Status da venda for PAGO, deve ter pagamento");
            }

            sale.setDatePayed(LocalDate.now());
        }
    }

    public void statusNotReceived(Sale sale) {
        if (sale.getStatus() == StatusSale.NOT_RECEIVED) {
            throw new IntegrityViolationException("Status da venda não deve ser NÃO PAGO");
        }
    }

    private void statusDue(Sale sale) {
        if (sale.getStatus() == StatusSale.DUE) {

            if (sale.getDatePayed() != null) {
                throw new IntegrityViolationException("Status da venda for DEVENDO, não deve ter data pago");
            }

            if (!sale.getPayments().isEmpty()) {
                throw new IntegrityViolationException("Status da venda for DEVENDO, não deve ter pagamento");
            }

        }
    }
}

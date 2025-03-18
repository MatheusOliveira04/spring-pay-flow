package git.MatheusOliveira04.services.strategy.impl;

import git.MatheusOliveira04.models.Sale;
import git.MatheusOliveira04.services.exception.IntegrityViolationException;
import git.MatheusOliveira04.services.strategy.StatusSaleStrategy;

public class DueStatusSaleStrategyImpl implements StatusSaleStrategy {

    @Override
    public void validateStatusSale(Sale sale) {
        checkSaleDatePayedIsNotNull(sale);
        checkSalePaymentsIsNotNullOrNotEmpty(sale);
    }

    private void checkSaleDatePayedIsNotNull(Sale sale) {
        if (sale.checkDatePayedIsNotNull()) {
            throw new IntegrityViolationException("Status da venda for DEVENDO, não deve ter data pago");
        }
    }

    private void checkSalePaymentsIsNotNullOrNotEmpty(Sale sale) {
        if (!sale.checkPaymentsIsNullOrEmpty()) {
            throw new IntegrityViolationException("Status da venda for DEVENDO, não deve ter pagamento");
        }
    }
}
